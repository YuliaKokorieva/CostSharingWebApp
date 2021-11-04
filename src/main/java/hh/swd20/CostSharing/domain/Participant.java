package hh.swd20.CostSharing.domain;

import javax.persistence.Entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
public class Participant {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long partID;
	
	private String partName;
	
	@ManyToOne
	@JsonIgnoreProperties("trip")
	@JoinColumn(name="tripID")
	private Trip trip;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="participant")
	@JsonIgnoreProperties("participant")
	private List<Expense> expenses;
	
	
	public Participant() {
		super();
		this.partName=null;
		this.trip=null;
	}
	
	public Participant(String partName, Trip trip) {
		super();
		this.partName=partName;
		this.trip=trip;
	}
	
	public Participant(Trip trip) {
		super();
		this.partName=null;
		this.trip=trip;
	}
	
	public Long getPartID() {
		return partID;
	}

	public void setPartID(Long partID) {
		this.partID = partID;
	}

	public String getPartName() {
		return partName;
	}

	public void setPartName(String partName) {
		this.partName = partName;
	}

	public Trip getTrip() {
		return trip;
	}

	public void setTrip(Trip trip) {
		this.trip = trip;
	}

	public List<Expense> getExpenses() {
		return expenses;
	}

	public void setExpenses(List<Expense> expenses) {
		this.expenses = expenses;
	}

	@Override
	public String toString() {
		return "Participant [partID =" + partID + ", name = " + partName + ", trip: "+ trip + "]";
	}

}
