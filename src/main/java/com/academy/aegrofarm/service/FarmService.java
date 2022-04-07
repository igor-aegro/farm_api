package com.academy.aegrofarm.service;

import com.academy.aegrofarm.entity.Farm;
import com.academy.aegrofarm.repository.FarmRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FarmService {

    private final FarmRepository farmRepository;

    public void addFarm(Farm farm){
        farmRepository.insert(farm);
    }
}
