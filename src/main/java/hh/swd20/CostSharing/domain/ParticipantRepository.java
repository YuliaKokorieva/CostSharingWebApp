package hh.swd20.CostSharing.domain;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface ParticipantRepository extends CrudRepository<Participant, Long> {
	List<Participant> findByPartName(String partName);
}
