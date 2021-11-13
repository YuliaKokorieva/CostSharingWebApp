package hh.swd20.CostSharing;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import hh.swd20.CostSharing.domain.Expense;
import hh.swd20.CostSharing.domain.ExpenseRepository;
import hh.swd20.CostSharing.domain.Participant;
import hh.swd20.CostSharing.domain.ParticipantRepository;
import hh.swd20.CostSharing.domain.Trip;
import hh.swd20.CostSharing.domain.TripRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@ExtendWith(SpringExtension.class) 
@DataJpaTest
public class RepositoryTests {
	@Autowired
	private TripRepository tripRepo;
	@Autowired
	private ParticipantRepository partRepo;
	@Autowired
	private ExpenseRepository expRepo;
	
	@Test
	public void findByNameShouldReturnTrip() {
		List<Trip> trips= tripRepo.findByTripName("Croatia");
		assertThat(trips).hasSize(1);
		assertThat(trips.get(0).getTripID()).isEqualTo(1);
	}
	
	@Test
	public void findByNameShouldReturnParticipant() {
		List<Participant> participants= partRepo.findByPartName("Anna");
		assertThat(participants).hasSize(1);
		assertThat(participants.get(0).getPartID()).isEqualTo(2);
	}
	
	@Test
	public void findByNameShouldReturnExpense() {
		List<Expense> expenses= expRepo.findByExpenseName("accomodation");
		assertThat(expenses).hasSize(1);
		assertThat(expenses.get(0).getExpenseID()).isEqualTo(5);
	}
	
	@Test
	public void createNewTrip() {
		Trip trip= new Trip("France");
		tripRepo.save(trip);
		assertThat(trip.getTripID()).isNotNull();
	}

	
	
}


