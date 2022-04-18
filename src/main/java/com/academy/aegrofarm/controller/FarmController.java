package com.academy.aegrofarm.controller;

import com.academy.aegrofarm.entity.Farm;
import com.academy.aegrofarm.service.FarmService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/farms")
@RequiredArgsConstructor
public class FarmController {

    @Autowired
    private final FarmService farmService;

    @PostMapping
    public ResponseEntity addFarm(@RequestBody Farm farm){
        farmService.addFarm(farm);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity updateFarm(@PathVariable("id") String id,@RequestBody Farm farm){
        Farm updatedFarm = farmService.updateFarm(id, farm);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteFarm(@PathVariable("id") String id){
        // TODO INSERT EXCEPTION IF ID NOT FOUND (VERIFY IF PUT IN CONTROLLER OR SERVICE)
        farmService.deleteFarm(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
