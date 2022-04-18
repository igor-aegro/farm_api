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

    @PostMapping("/{id}/glebe/")
    public ResponseEntity addGlebe(@PathVariable("id") String id, @RequestBody Glebe glebe){
        glebeService.addGlebe(id, glebe);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
