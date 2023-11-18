package com.connectis.connectis.services;

import com.connectis.connectis.models.CountryEntity;
import com.connectis.connectis.repository.ICountryRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class CountryServices {
    @Autowired
    private ICountryRepository iCountryRepository;

    public CountryEntity addCountry(String name){
        return null;
    }
}
