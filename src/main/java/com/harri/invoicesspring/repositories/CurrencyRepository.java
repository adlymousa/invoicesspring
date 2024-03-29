package com.harri.invoicesspring.repositories;

import com.harri.invoicesspring.models.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency, Integer> {

    Currency findByName(String name);

}
