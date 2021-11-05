package hh.swd20.CostSharing.domain;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface TripRepository extends CrudRepository<Trip, Long> {
	List<Trip> findByTripName(String tripName);
}
