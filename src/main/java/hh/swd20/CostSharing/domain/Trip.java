package hh.swd20.CostSharing.domain;

import javax.persistence.Entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Trip {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long tripID;
	@NotBlank(message = "trip name cannot be null")
	private String tripName;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="trip")
	@JsonIgnoreProperties({"trip", "expenses", "participants"})
	private List<Participant> participants;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="trip")
	@JsonIgnoreProperties({"trip", "expenses", "participants"})
	private List<Expense> expenses;
	
	public Trip() {
		super();
		this.tripName=null;
	}
	public Trip(String tripName) {
		super();
		this.tripName=tripName;
	}
	public Long getTripID() {
		return tripID;
	}
	public void setTripID(Long tripID) {
		this.tripID = tripID;
	}
	public String getTripName() {
		return tripName;
	}
	public void setTripName(String tripName) {
		this.tripName = tripName;
	}
	public List<Participant> getParticipants() {
		return participants;
	}
	public void setParticipants(List<Participant> participants) {
		this.participants = participants;
	}
	public List<Expense> getExpenses() {
		return expenses;
	}
	public void setExpenses(List<Expense> expenses) {
		this.expenses = expenses;
	}
	
	@Override
	public String toString() {
		return "Trip [tripID =" + tripID + ", name = " + tripName + "]";
	}

}
