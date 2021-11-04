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
public class RESTController {
	
	private static final Logger log = LoggerFactory.getLogger(CostSharingApplication.class);
	
	@Autowired
	private TripRepository tripRepo;
	@Autowired
	private ParticipantRepository partRepo;
	@Autowired
	private ExpenseRepository expRepo;
	
	@GetMapping(value="/apitrips")
	public @ResponseBody List<Trip> tripListRest() {
		return (List<Trip>) tripRepo.findAll();
	}
	
	@GetMapping(value="/apitrips/{id}")
	public @ResponseBody Optional<Trip> findTripRest(@PathVariable("id") Long tripID) {
		return tripRepo.findById(tripID);
	}
	
	@PostMapping(value="/apitrips") 
		public @ResponseBody Trip saveTripRest(@RequestBody Trip trip) {
			return tripRepo.save(trip);	
	}
	
	@PostMapping(value="/apiparticipants") 
	public @ResponseBody Participant savePartRest(@RequestBody Participant participant) {
		return partRepo.save(participant);	
	}
	
	@GetMapping(value="/apiparticipants") 
	public @ResponseBody List<Participant> partListRest() {
		return (List<Participant>) partRepo.findAll();	
	}
	
	@GetMapping(value="/apiparticipants/{id}")
	public @ResponseBody Optional<Participant> findPartRest(@PathVariable("id") Long partID) {
		return partRepo.findById(partID);
	}
	
	@GetMapping(value="/apiexpenses") 
	public @ResponseBody List<Expense> partExpRest() {
		return (List<Expense>) expRepo.findAll();	
	}
	
	@PostMapping(value="/apiexpenses") 
	public @ResponseBody Expense saveExpRest(@RequestBody Expense exp) {
		return expRepo.save(exp);	
	}
	
	@GetMapping(value="/apiexpenses/{id}")
	public @ResponseBody Optional<Expense> findExpRest(@PathVariable("id") Long expID) {
		return expRepo.findById(expID);
	}


}
