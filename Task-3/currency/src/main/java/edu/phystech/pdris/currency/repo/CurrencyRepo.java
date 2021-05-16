package edu.phystech.pdris.currency.repo;

import edu.phystech.pdris.currency.model.Currency;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyRepo extends CrudRepository<Currency, String> {

}
