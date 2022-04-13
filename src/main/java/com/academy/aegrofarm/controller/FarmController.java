package com.academy.aegrofarm.controller;

import com.academy.aegrofarm.entity.Farm;
import com.academy.aegrofarm.exception.ApiRequestException;
import com.academy.aegrofarm.service.FarmService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
public class FarmController {

    @Autowired
    private final FarmService farmService;

    @GetMapping("/farms")
    public List<Farm> getAllFarms(){
//        throw new ApiRequestException(("Não encontramos as fazendas!"));
        return farmService.getAllFarms();
    }

    @GetMapping("/farm/{name}")
    public Farm getFarmByName(@PathVariable("name") String name){
        return farmService.getFarmByName(name);
    }

    @GetMapping("/farm/{id}")
    public Farm getFarmById(@PathVariable("id") String id){
        return farmService.getFarmById(id);
    }

    @PostMapping
    public ResponseEntity addFarm(Farm farm){
        farmService.addFarm(farm);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/farm/{id}")
    public ResponseEntity updateFarm(@PathVariable("id") String id, Farm farm){
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
