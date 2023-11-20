package com.connectis.connectis.services;

import com.connectis.connectis.models.CountryEntity;
import com.connectis.connectis.repository.ICountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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
    public boolean deleteCountryById(Long id) {
        Optional<CountryEntity> countryOptional = iCountryRepository.findById(id);
        if (countryOptional.isPresent()) {
            iCountryRepository.deleteById(id);
            return true;
        }
        return false;
    }
    public CountryEntity updateCountry(CountryEntity country){
        Optional<CountryEntity> OptionalCountryToUpdate = iCountryRepository.findById(country.getId());
        if (OptionalCountryToUpdate.isPresent()) {
            CountryEntity existingCountry = OptionalCountryToUpdate.get();
            existingCountry.setName(country.getName());
            return iCountryRepository.save(existingCountry);
        }
        return null;
    }
}
