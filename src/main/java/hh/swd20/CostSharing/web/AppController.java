package hh.swd20.CostSharing.web;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import hh.swd20.CostSharing.CostSharingApplication;
import hh.swd20.CostSharing.domain.Expense;
import hh.swd20.CostSharing.domain.ExpenseRepository;
import hh.swd20.CostSharing.domain.Participant;
import hh.swd20.CostSharing.domain.ParticipantRepository;
import hh.swd20.CostSharing.domain.Trip;
import hh.swd20.CostSharing.domain.TripRepository;

@Controller
public class AppController {
	
	private static final Logger log = LoggerFactory.getLogger(CostSharingApplication.class);
	
	@Autowired
	private TripRepository tripRepo;
	@Autowired
	private ParticipantRepository partRepo;
	@Autowired
	private ExpenseRepository expRepo;
	
	@GetMapping(value = {"", "/", "/trips"})
	public String getAllTrips(Model model) {
		model.addAttribute("trips", tripRepo.findAll());
		return "trips";
	}
	
	@GetMapping(value="trips/{id}")
	public String getTrip(@PathVariable("id") Long tripID, Model model) {
		Trip trip = tripRepo.findById(tripID).get();
		model.addAttribute("expenses", trip.getExpenses());
		model.addAttribute("trip", trip);
		model.addAttribute("participants", trip.getParticipants());
		model.addAttribute("summary", calculateSummary(tripID));
		return "tripinfo";
	}
	
	@GetMapping(value = "trips/{id}/addexpense")
	public String addExpense(@PathVariable("id") Long tripID, Model model) {
		model.addAttribute("expense", new Expense(tripRepo.findById(tripID).get()));
		model.addAttribute("participants", tripRepo.findById(tripID).get().getParticipants());
		return "addexpense";
	}
	
	@PostMapping(value="/saveexpense", params= {"save"})
	public String saveExp(Expense expense) {
		expRepo.save(expense);
		return "redirect:/trips/" + expense.getTrip().getTripID();
	}
	
	@PostMapping(value={"/saveexpense", "/savetrip", "/saveparticipant"}, params= {"cancel"})
	public String back() {
		return "redirect:/trips";
	}
	
	@GetMapping(value="/addtrip")
	public String addTrip(Model model) {
		model.addAttribute("trip", new Trip());
		return "addtrip";
	}
	
	@PostMapping(value="/savetrip", params= {"save"})
	public String saveTrip(Trip trip) {
		tripRepo.save(trip);
		return "redirect:/trips";
	}
	
	@GetMapping(value="/delete/{id}")
	public String deleteItem(@PathVariable("id") Long id) {
		String path = "";

		if (tripRepo.findById(id).isPresent()) {
			tripRepo.deleteById(id);
		} else if (partRepo.findById(id).isPresent()) {
			path=Long.toString(partRepo.findById(id).get().getTrip().getTripID());
			partRepo.deleteById(id);
		} else if (expRepo.findById(id).isPresent()){
			path=Long.toString(expRepo.findById(id).get().getTrip().getTripID());
			expRepo.deleteById(id);
		} 
		return "redirect:/trips/"+path;
	}
	
	@GetMapping(value="/edittrip/{id}")
	public String editTrip(@PathVariable("id") Long id, Model model) {
		Trip trip = tripRepo.findById(id).get();
		model.addAttribute("trip", trip);
		model.addAttribute("participants",trip.getParticipants());
		return "/edittrip";
	}
	
	@GetMapping(value="/editexp/{id}")
	public String editExp(@PathVariable("id") Long id, Model model) {
		model.addAttribute("expense", expRepo.findById(id).get());
		model.addAttribute("participants", expRepo.findById(id).get().getTrip().getParticipants());
		return "/addexpense";
	}
	
	@GetMapping(value = "trips/{id}/addparticipant")
	public String addParticipant(@PathVariable("id") Long tripID, Model model) {
		model.addAttribute("participant", new Participant(tripRepo.findById(tripID).get()));
		return "addparticipant";
	}
	
	@PostMapping(value="/saveparticipant", params= {"save"})
	public String savePart(Participant part) {
		partRepo.save(part);
		String path=Long.toString(part.getTrip().getTripID());
		return "redirect:/trips/"+path;
	}
	
	@GetMapping(value="/editpart/{id}")
	public String editPart(@PathVariable("id") Long id, Model model) {
		model.addAttribute("participant", partRepo.findById(id).get());
		return "/addparticipant";
	}
	
	public String calculateSummary(Long tripID) {
		String summaryText = "";
        Double partPaid=0.0;
        Double total = 0.0;
        Map<String, Double> paidByParticipant = new TreeMap<String, Double>();
        
        List<Participant> partList = tripRepo.findById(tripID).get().getParticipants();
        for (Participant part : partList) {
        	List<Expense> partExpenses = part.getExpenses();
        	partPaid=0.0;
        	for (Expense exp : partExpenses) {
        		total+=exp.getExpValue();
        		partPaid+=exp.getExpValue();    		
        	}
        	paidByParticipant.put(part.getPartName(), partPaid);
        }
        
        Double equalShare = total / paidByParticipant.size();
        
        summaryText+="Total is " + String.format("%.2f", total) + ". ";
        
        for (Map.Entry m : paidByParticipant.entrySet()) {
            partPaid = (Double) m.getValue();
            Double result = partPaid - equalShare;
            if (result>0) {
                summaryText+=(String) m.getKey() + " is owed " + String.format("%.2f", result)+ ". ";
            } else if (result<0) {
                summaryText+=(String) m.getKey() + " owes " + String.format("%.2f", -result) + ". ";
            } else {
                summaryText+=(String) m.getKey() + " doesn't owe and is not owed anything. ";
            }
        }
        
        return summaryText;	
	}
	
	
	
	
	

}
