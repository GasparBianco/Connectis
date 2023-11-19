package com.connectis.connectis;

import com.connectis.connectis.models.CountryEntity;
import com.connectis.connectis.services.CountryServices;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

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
    public void getCountryByExactName_ValidName_countryEntity(){
        CountryEntity country = countryServices.getCountryByExactName("Argentina");
        assertEquals("Argentina", country.getName());
        assertEquals(1L, country.getId());
    }
    @Test
    public void getCountryByExactName_invalidName_null(){
        assertNull(countryServices.getCountryByExactName("argentina"));
        assertNull(countryServices.getCountryByExactName("Argentine"));
    }
    @Test
    public void getCountryByNameContaining_validName_listCountryEntity(){
        CountryEntity addedCountry = new CountryEntity();
        addedCountry.setName("Armenia");
        countryServices.addCountry(addedCountry);
        List<CountryEntity> countryList = countryServices.getCountryByNameContaining("ar");
        assertNotNull(countryList);
        assertEquals(2, countryList.size());
        assertTrue(countryList.stream().anyMatch(country -> country.getName().equals("Argentina")));
        assertTrue(countryList.stream().anyMatch(country -> country.getName().equals("Armenia")));
    }
    @Test
    public void getCountryByNameContaining_invalidName_null(){
        assertNull(countryServices.getCountryByNameContaining("xz"));
    }
    @Test
    public void getCountryById_invalidId_null(){
        assertNull(countryServices.getCountryById(5L));
    }
}
