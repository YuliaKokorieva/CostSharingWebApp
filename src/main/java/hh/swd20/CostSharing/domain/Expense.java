package hh.swd20.CostSharing.domain;

import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Expense {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long expenseID;
	@NotNull(message = "expense name cannot be null")
	private String expenseName;
	
	@ManyToOne
	@JoinColumn(name="tripID")
	@JsonIgnoreProperties({"trip", "participants", "expenses"})
	private Trip trip;
	
	@ManyToOne
	@JoinColumn(name="partID")
	@JsonIgnoreProperties({"expenses", "trip", "participant"})
	private Participant participant;
	@Positive(message="value cannot be negative or 0")
	private Double expValue;
	
	public Expense() {
		super();
		this.expenseName=null;
		this.trip=null;
		this.expValue=0.0;
		this.participant=null;
	}
	public Expense(Trip trip) {
		super();
		this.expenseName=null;
		this.trip=trip;
		this.expValue=0.0;
		this.participant=null;
	}
	
	public Expense(String expName, Double expValue, Participant participant, Trip trip) {
		super();
		this.expenseName=expName;
		this.trip=trip;
		this.expValue=expValue;
		this.participant=participant;
	}

	public Long getExpenseID() {
		return expenseID;
	}

	public void setExpenseID(Long expenseID) {
		this.expenseID = expenseID;
	}

	public String getExpenseName() {
		return expenseName;
	}

	public void setExpenseName(String expenseName) {
		this.expenseName = expenseName;
	}

	public Trip getTrip() {
		return trip;
	}

	public void setTrip(Trip trip) {
		this.trip = trip;
	}

	public Participant getParticipant() {
		return participant;
	}

	public void setParticipant(Participant participant) {
		this.participant = participant;
	}

	public Double getExpValue() {
		return expValue;
	}

	public void setExpValue(Double expValue) {
		this.expValue = expValue;
	}

	@Override
	public String toString() {
		return "Expense [expID =" + expenseID + ", value = " + expValue + ", participant: " + participant + ", trip: " + trip +"]";
	}

}
