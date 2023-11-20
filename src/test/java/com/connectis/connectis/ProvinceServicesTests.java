package com.connectis.connectis;

import com.connectis.connectis.models.CountryEntity;
import com.connectis.connectis.models.ProvinceEntity;
import com.connectis.connectis.services.CountryServices;
import com.connectis.connectis.services.ProvinceServices;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ProvinceServicesTests {
    @Autowired
    private ProvinceServices provinceServices;
    @Autowired
    CountryServices countryServices;

    @Test
    public void addProvince_validProvince_provinceEntity(){
        CountryEntity country = new CountryEntity();
        country.setName("Argentina");
        country = countryServices.addCountry(country);

        ProvinceEntity province = new ProvinceEntity();
        province.setName("Cordoba");
        province.setCountryEntity(country);
        ProvinceEntity addedProvince = provinceServices.addProvince(province);
        assertNotNull(addedProvince.getId());
        assertEquals("Cordoba", addedProvince.getName());
        assertEquals("Argentina", addedProvince.getCountryEntity().getName());
    }
    @Test
    public void getProvinceById_validId_provinceEntity(){
        ProvinceEntity province = new ProvinceEntity();
        province.setName("Santa Fe");
        province.setCountryEntity(countryServices.getCountryById(1L));
        province = provinceServices.addProvince(province);
        assertEquals("Santa Fe", provinceServices.getProvinceById(province.getId()).getName());
    }
    @Test
    public void getProvinceByExactName_validName_ProvinceEntity(){
        ProvinceEntity province = new ProvinceEntity();
        province.setName("Neuquen");
        province.setCountryEntity(countryServices.getCountryByExactName("Argentina"));
        provinceServices.addProvince(province);
        assertEquals("Neuquen" ,provinceServices.getProvinceByExactName("Neuquen").getName());
    }
    @Test
    public void getProvinceById_invalidId_null(){
        assertNull(provinceServices.getProvinceById(999L));
    }
    @Test
    public void getProvinceByExactName_nonexistentName_null(){
        ProvinceEntity province = new ProvinceEntity();
        province.setName("Chaco");
        province.setCountryEntity(countryServices.getCountryByExactName("Argentina"));
        provinceServices.addProvince(province);
        assertNull(provinceServices.getProvinceByExactName("chaco"));
        assertNull(provinceServices.getProvinceByExactName("Chako"));
    }
    @Test
    public void getProvinceByNameContaining_validName_listProvinceEntity(){
        ProvinceEntity province = new ProvinceEntity();
        province.setName("Buenos Aires");
        provinceServices.addProvince(province);
        ProvinceEntity province2 = new ProvinceEntity();
        province2.setName("Jujuy");
        provinceServices.addProvince(province2);
        List<ProvinceEntity> provinceList = provinceServices.getProvinceByNameContaining("u");
        assertNotNull(provinceList);
        assertTrue(provinceList.stream().anyMatch(prov -> prov.getName().equals("Buenos Aires")));
        assertTrue(provinceList.stream().anyMatch(prov -> prov.getName().equals("Jujuy")));
    }
    @Test
    public  void getProvinceByNameContaining_nonexistentName_null(){
        assertNull(provinceServices.getProvinceByNameContaining("xz"));
    }
    @Test
    public void deleteProvinceById_validId_true(){
        ProvinceEntity province = new ProvinceEntity();
        province.setName("Rio Negro");
        province = provinceServices.addProvince(province);
        assertNotNull(province);
        assertTrue(provinceServices.deleteProvinceById(province.getId()));
        assertNull(provinceServices.getProvinceById(province.getId()));
    }
    @Test
    public void deleteProvinceById_nonexistentId_false(){
        assertFalse(provinceServices.deleteProvinceById(999L));
    }
    @Test
    public void updateProvince_validId_provinceEntity(){
        CountryEntity country1 = new CountryEntity();
        country1.setName("Argentina");
        country1 = countryServices.addCountry(country1);
        CountryEntity country2 = new CountryEntity();
        country2.setName("Brasil");
        country2 = countryServices.addCountry(country2);

        ProvinceEntity provinceToUpdate = new ProvinceEntity();
        provinceToUpdate.setCountryEntity(country1);
        provinceToUpdate.setName("Misiones");
        provinceToUpdate = provinceServices.addProvince(provinceToUpdate);
        provinceToUpdate.setName("Rio do janeiro");
        provinceToUpdate.setCountryEntity(country2);
        ProvinceEntity updatedProvince = provinceServices.updateProvince(provinceToUpdate);
        assertEquals(provinceToUpdate.getId(), updatedProvince.getId());
        assertEquals(country2.getName(), updatedProvince.getCountryEntity().getName());
        assertEquals("Rio do janeiro", updatedProvince.getName());
    }
    @Test
    public void updateProvince_nonexistentId_null(){
        ProvinceEntity province = new ProvinceEntity();
        province.setId(999L);
        assertNull(provinceServices.updateProvince(province));
    }
}
