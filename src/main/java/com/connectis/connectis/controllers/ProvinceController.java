package com.connectis.connectis.controllers;

import com.connectis.connectis.dto.ProvinceDto;
import com.connectis.connectis.models.ProvinceEntity;
import com.connectis.connectis.services.ProvinceService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/province/")
public class ProvinceController {

    @Autowired
    private ProvinceService provinceService;

    @PostMapping
    public ResponseEntity<ProvinceDto> addProvince(@RequestBody ProvinceDto provinceDto){
        return new ResponseEntity<>(provinceService.addProvince(provinceDto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ProvinceDto>> getAllProvinces(){
        return new ResponseEntity<>(provinceService.getAllProvinces(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProvinceDto> getProvinceById(@PathVariable Long id){
        return new ResponseEntity<>(provinceService.getProvinceById(id), HttpStatus.OK);
    }

    @GetMapping("/exact/{name}")
    public ResponseEntity<ProvinceDto> getProvinceByExactName(@PathVariable String name){
        ProvinceDto provinceDto = provinceService.getProvinceByExactName(name);
        return new ResponseEntity<>(provinceDto, HttpStatus.OK);
    }

    @GetMapping("/contains/{name}")
    public ResponseEntity<List<ProvinceDto>> getProvinceByContainingName(@PathVariable String name){
        List<ProvinceDto> province = provinceService.getProvinceByNameContaining(name);
        return new ResponseEntity<>(province, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProvinceById(@PathVariable Long id) {
        provinceService.deleteProvinceById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
