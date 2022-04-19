package com.academy.aegrofarm.controller;

import com.academy.aegrofarm.entity.Glebe;
import com.academy.aegrofarm.service.GlebeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/farms")
public class GlebeController {

    @Autowired
    private final GlebeService glebeService;

    @PostMapping("/{farmId}/glebe/")
    public ResponseEntity addGlebe(@PathVariable("farmId") String farmId, @RequestBody Glebe glebe){
        glebeService.addGlebe(farmId, glebe);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{farmId}/glebe/{glebeId}")
    public ResponseEntity updateGlebe(@PathVariable("farmId") String farmId,
                                   @PathVariable("glebeId") String glebeId,
                                   @RequestBody Glebe glebe){
        Glebe updatedGlebe = glebeService.updateGlebe(farmId, glebeId, glebe);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{farmId}/glebe/{glebeId}")
    public ResponseEntity deleteGlebe(@PathVariable("farmId") String farmId,
                                      @PathVariable("glebeId") String glebeId){
        glebeService.deleteGlebe(farmId, glebeId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }


}
