package com.harri.invoicesspring.services;


import com.harri.invoicesspring.models.Currency;
import com.harri.invoicesspring.repositories.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CurrencyService {

    @Autowired
    CurrencyRepository currencyRepository;

    public Currency getCurrencyByName(String name){
        return currencyRepository.findByName(name);
    }

}
