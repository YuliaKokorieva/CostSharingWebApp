package hh.swd20.CostSharing;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import hh.swd20.CostSharing.domain.Trip;
import hh.swd20.CostSharing.domain.TripRepository;
import hh.swd20.CostSharing.domain.User;
import hh.swd20.CostSharing.domain.UserRepository;
import hh.swd20.CostSharing.domain.Participant;
import hh.swd20.CostSharing.domain.ParticipantRepository;
import hh.swd20.CostSharing.domain.Expense;
import hh.swd20.CostSharing.domain.ExpenseRepository;

@SpringBootApplication
public class CostSharingApplication {
	private static final Logger log = LoggerFactory.getLogger(CostSharingApplication.class);
	
	@Autowired
	private TripRepository tripRepo;
	@Autowired
	private ParticipantRepository partRepo;
	@Autowired
	private ExpenseRepository expRepo;
	
	public static void main(String[] args) {
		SpringApplication.run(CostSharingApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner costSharingDemo(UserRepository uRepo) {
		return (args) -> {
			
			log.info("saving first trip");
			
			// Create users: admin/admin user/user
			User user1 = new User("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER", "user@book.fi");
			User user2 = new User("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "ADMIN", "email@book.fi");
			uRepo.save(user1);
			uRepo.save(user2);
			log.info("users created: " + user1.getRole() + user2.getRole());
			
			Trip Croatia = new Trip("Croatia");
			tripRepo.save(Croatia);
			
			log.info("creating 3 participants");
			Participant Anna = new Participant("Anna", Croatia); 
			partRepo.save(Anna);
			Participant Maria = new Participant("Maria", Croatia);
			partRepo.save(Maria);
			Participant Elena = new Participant("Elena", Croatia);
			partRepo.save(Elena);
			
			log.info("fetching created participants");
			for (Participant part : partRepo.findAll()) {
				log.info(part.toString());
			}
			
			log.info("creating first expenses");
			expRepo.save(new Expense("accomodation", 290.4, Anna, Croatia));
			expRepo.save(new Expense("car rental", 300.0, Maria, Croatia));
			expRepo.save(new Expense("lunch", 89.6, Elena, Croatia));
			
			log.info("fetching created expenses");
			for (Expense exp : expRepo.findAll()) {
				log.info(exp.toString());
			}
		};
	}

}
