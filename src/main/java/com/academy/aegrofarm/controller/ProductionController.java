package com.academy.aegrofarm.controller;

import com.academy.aegrofarm.entity.Production;
import com.academy.aegrofarm.service.ProductionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/glebes")
public class ProductionController {

    @Autowired
    private final ProductionService productionService;

    @PostMapping("/{glebeId}/production/")
    public ResponseEntity addProduction(@PathVariable("glebeId") String glebeId, @RequestBody Production production){
        productionService.addProduction(glebeId, production);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{glebeId}/production/{productionId}")
    public ResponseEntity updateProduction(@PathVariable("glebeId") String glebeId,
                                      @PathVariable("productionId") String productionId,
                                      @RequestBody Production production){
        Production updatedProduction = productionService.updateProduction(glebeId, productionId, production);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{glebeId}/production/{productionId}")
    public ResponseEntity deleteProduction(@PathVariable("glebeId") String glebeId,
                                      @PathVariable("productionId") String productionId){
        productionService.deleteProduction(glebeId, productionId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
