package de.ing.creditapplication;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import de.ing.creditapplication.models.CreditApplication;

public interface CreditApplicationRepository extends CrudRepository<CreditApplication, String> {

	@Query("Select c from CreditApplication c")
	List<CreditApplication> findAllAsList();
}
