package com.connectis.connectis;

import com.connectis.connectis.models.CountryEntity;
import com.connectis.connectis.repository.ICountryRepository;
import com.connectis.connectis.services.CountryServices;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CountryServicesTests {
    @Autowired
    private CountryServices countryServices;
    @Autowired
    private ICountryRepository iCountryRepository;

    @Test
    public void addCountry_validName_countryResponse(){
        CountryEntity addedCountry = countryServices.addCountry("Argentina");
        assertNotNull(addedCountry.getId());
        assertEquals("Argentina", addedCountry.getName());
    }
}
