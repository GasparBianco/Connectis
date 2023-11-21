package com.connectis.connectis.controllers;

import com.connectis.connectis.dto.ProvinceDto;
import com.connectis.connectis.models.ProvinceEntity;
import com.connectis.connectis.services.ProvinceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/country/")
@RequiredArgsConstructor
public class ProvinceController {

    private final ProvinceService provinceService;

    @PostMapping
    public ResponseEntity<ProvinceDto> addProvince(@RequestBody ProvinceDto provinceDto){
        return new ResponseEntity<>(provinceService.addProvince(provinceDto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ProvinceEntity>> getAllProvinces(){
        return new ResponseEntity<>(provinceService.getAllProvinces(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProvinceEntity> getProvinceById(@PathVariable Long id) throws Exception {
        return new ResponseEntity<>(provinceService.getCountryById(id), HttpStatus.OK);
    }

    @GetMapping("/exact/{name}")
    public ResponseEntity<ProvinceEntity> getProvinceByExactName(@PathVariable String name){
        ProvinceEntity country = provinceService.getCountryByExactName(name);
        if (country != null) {
            return new ResponseEntity<>(country, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/contains/{name}")
    public ResponseEntity<List<ProvinceEntity>> getProvinceByContainingName(@PathVariable String name){
        List<ProvinceEntity> province = provinceService.getCountryByNameContaining(name);
        if (province != null) {
            return new ResponseEntity<>(province, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<List<ProvinceEntity>> deleteProvinceById(@PathVariable Long id){
        boolean province = provinceService.deleteCountryById(id);
        if (province) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
