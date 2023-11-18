package com.connectis.connectis;

import com.connectis.connectis.models.CountryEntity;
import com.connectis.connectis.services.CountryServices;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CountryServicesTests {
    @Autowired
    private CountryServices countryServices;

    @Test
    public void addCountry_validName_countryEntity(){
        CountryEntity country = new CountryEntity();
        country.setName("Argentina");
        CountryEntity addedCountry = countryServices.addCountry(country);
        assertNotNull(addedCountry.getId());
        assertEquals("Argentina", addedCountry.getName());
    }
    @Test
    public void getCountryById_ValidId_countryEntity(){
        assertEquals("Argentina", countryServices.getCountryById(1L).getName());
    }
    @Test
    public void getCountryById_invalidId_null(){
        assertNull(countryServices.getCountryById(5L));
    }
}
