package com.connectis.connectis;

import com.connectis.connectis.dto.ProvinceDto;
import com.connectis.connectis.exception.ProvinceNotFoundException;
import com.connectis.connectis.services.ProvinceService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ProvinceServicesTests {

    @Autowired
    private ProvinceService provinceService;

    @Test
    public void addProvince_validProvince_provinceDto(){
        ProvinceDto provinceDto = ProvinceDto.builder().name("Cordoba").build();
        ProvinceDto addedProvince = provinceService.addProvince(provinceDto);
        assertNotNull(addedProvince.getId());
        assertEquals(provinceDto.getName(), addedProvince.getName());
    }

    @Test
    public void addCountry_nameShort_exception(){
        ProvinceDto provinceDto = ProvinceDto.builder()
                .name("cba")
                .build();
        assertThrows(IllegalArgumentException.class, () -> provinceService.addProvince(provinceDto));
    }

    @Test
    public void addCountry_nameNull_exception(){
        ProvinceDto provinceDto = ProvinceDto.builder()
                .name(null)
                .build();
        assertThrows(IllegalArgumentException.class, () -> provinceService.addProvince(provinceDto));
    }
    @Test
    public void getProvinceById_ValidId_provinceEntity() {
        ProvinceDto provinceDto = ProvinceDto.builder().name("San Luis").build();
        ProvinceDto addedProvince = provinceService.addProvince(provinceDto);
        assertEquals(provinceDto.getName(), provinceService.getProvinceById(addedProvince.getId()).getName());
    }

    @Test
    public void getCountryById_nonexistentId_exception(){
        assertThrows(ProvinceNotFoundException.class, () -> provinceService.getProvinceById(999L));
    }

    @Test
    public void getProvinceByExactName_ValidName_provinceEntity(){
        ProvinceDto provinceDto = ProvinceDto.builder().name("Santa Fe").build();
        provinceService.addProvince(provinceDto);
        ProvinceDto addedProvince = provinceService.getProvinceByExactName(provinceDto.getName());
        assertEquals(provinceDto.getName(), addedProvince.getName());
    }
    @Test
    public void getProvinceByExactName_nonexistentName_exception(){
        ProvinceDto provinceDto = ProvinceDto.builder().name("Cordoba").build();
        provinceService.addProvince(provinceDto);
        assertThrows(ProvinceNotFoundException.class,()-> provinceService.getProvinceByExactName("cordoba"));
    }
    @Test
    public void getProvinceByNameContaining_validName_listProvinceEntity(){
        List<String> provinceNameList = Arrays.asList("Neuquen", "Jujuy","Santa Cruz", "Buenos Aires", "Mendoza", "Cordoba");
        for (String provinceName:provinceNameList) {
            ProvinceDto addedProvince = ProvinceDto.builder()
                    .name(provinceName)
                    .build();
            provinceService.addProvince(addedProvince);
        }
        List<ProvinceDto> provinceDtoList = provinceService.getProvinceByNameContaining("u");
        assertNotNull(provinceDtoList);
        assertEquals(4, provinceDtoList.size());
        for (String provinceName:provinceNameList) {
            if (provinceName.contains("u")){
                assertTrue(provinceDtoList.stream().anyMatch(province -> province.getName().equals(provinceName)));
            }else{
                assertFalse(provinceDtoList.stream().anyMatch(province -> province.getName().equals(provinceName)));
            }
        }
    }
    @Test
    public void getProvinceByNameContaining_nonexistentName_exception(){
        assertThrows(ProvinceNotFoundException.class, () ->
                provinceService.getProvinceByNameContaining("xz"));
    }
    @Test
    public void deleteById_validId_void(){
        ProvinceDto provinceDto = ProvinceDto.builder()
                .name("Formosa")
                .build();
        ProvinceDto addedProvince = provinceService.addProvince(provinceDto);
        assertDoesNotThrow(() -> provinceService.deleteProvinceById(addedProvince.getId()));
        assertThrows(ProvinceNotFoundException.class, () ->
                provinceService.getProvinceById(addedProvince.getId()));
    }
    @Test
    public void deleteById_nonexistentId_exception(){
        assertThrows(ProvinceNotFoundException.class, () -> provinceService.getProvinceById(999L));
        assertThrows(ProvinceNotFoundException.class, () -> provinceService.deleteProvinceById(999L));
    }
}
