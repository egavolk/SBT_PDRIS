package edu.phystech.pdris.hw.repo;

import edu.phystech.pdris.hw.model.Currency;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyRepo extends CrudRepository<Currency, String> {

}
