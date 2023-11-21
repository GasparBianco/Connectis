package com.connectis.connectis;

import com.connectis.connectis.dto.ProvinceDto;
import com.connectis.connectis.models.ProvinceEntity;
import com.connectis.connectis.services.ProvinceService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ProvinceServicesTests {
    @Autowired
    private ProvinceService provinceService;

    @Test
    public void addProvince_validProvince_countryEntity(){
        ProvinceDto country = ProvinceDto.builder().name("Cordoba").build();
        ProvinceDto addedProvince = provinceService.addProvince(country);
        assertNotNull(addedProvince.getId());
        assertEquals("Cordoba", addedProvince.getName());
    }
    @Test
    public void getProvinceById_ValidId_provinceEntity() throws Exception {
        assertEquals("Cordoba", provinceService.getProvinceById(1L).getName());
    }
    @Test
    public void getProvinceByExactName_ValidName_provinceEntity(){
        ProvinceEntity province = provinceService.getProvinceByExactName("Cordoba");
        assertEquals("Cordoba", province.getName());
        assertEquals(1L, province.getId());
    }
    @Test
    public void getProvinceByExactName_invalidName_null(){
        assertNull(provinceService.getProvinceByExactName("cordoba"));
        assertNull(provinceService.getProvinceByExactName("cordobe"));
    }
    @Test
    public void getProvinceByNameContaining_validName_listProvinceEntity(){
        ProvinceDto addedProvince = ProvinceDto.builder()
                .name("Armenia")
                .build();
        provinceService.addProvince(addedProvince);
        List<ProvinceEntity> provinceEntityList = provinceService.getProvinceByNameContaining("ar");
        assertNotNull(provinceEntityList);
        assertEquals(2, provinceEntityList.size());
        assertTrue(provinceEntityList.stream().anyMatch(province -> province.getName().equals("Argentina")));
        assertTrue(provinceEntityList.stream().anyMatch(province -> province.getName().equals("Armenia")));
    }
    @Test
    public void getProvinceByNameContaining_invalidName_null(){
        assertNull(provinceService.getProvinceByNameContaining("xz"));
    }
    @Test
    public void deleteById_validId_true() throws Exception {
        assertTrue(provinceService.deleteProvinceById(1L));
        assertNull(provinceService.getProvinceById(1L));
    }
    @Test
    public void deleteById_invalidId_false(){
        assertFalse(provinceService.deleteProvinceById(999L));
    }
    @Test
    public void getCountryById_invalidId_null() throws Exception {
        assertNull(provinceService.getProvinceById(999L));
    }
}
