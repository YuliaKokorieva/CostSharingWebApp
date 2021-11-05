package hh.swd20.CostSharing.domain;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface ExpenseRepository extends CrudRepository<Expense, Long> {
	List<Expense> findByExpenseName(String expenseName);
}
