package com.connectis.connectis.services;

import com.connectis.connectis.models.CountryEntity;
import com.connectis.connectis.repository.ICountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CountryServices {
    @Autowired
    private ICountryRepository iCountryRepository;

    public CountryEntity addCountry(CountryEntity country){
        return iCountryRepository.save(country);
    }
    public CountryEntity getCountryById(Long countryId){
        return iCountryRepository.findById(countryId).orElse(null);
    }
    public CountryEntity getCountryByExactName(String name){
        return iCountryRepository.findByName(name);
    }
    public List<CountryEntity> getCountryByNameContaining(String name){
        List<CountryEntity> countries = iCountryRepository.findByNameContainingIgnoreCase(name);
        return countries.isEmpty() ? null : countries;
    }
}
