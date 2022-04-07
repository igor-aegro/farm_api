package com.academy.aegrofarm.controller;

import com.academy.aegrofarm.entity.Farm;
import com.academy.aegrofarm.service.FarmService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/farms")
@RequiredArgsConstructor
public class FarmController {

    private final FarmService farmService;

    @PostMapping
    public ResponseEntity addFarm(@RequestBody Farm farm){
        farmService.addFarm(farm);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
