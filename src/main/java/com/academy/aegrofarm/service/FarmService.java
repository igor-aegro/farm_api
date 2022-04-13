package com.academy.aegrofarm.service;

import com.academy.aegrofarm.entity.Farm;
import com.academy.aegrofarm.repository.FarmRepository;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FarmService {

    private final FarmRepository farmRepository;

    public Farm addFarm(Farm farm){
        return farmRepository.insert(farm);
    }

    public Farm updateFarm(String id, @NotNull Farm farm) {
        farm.setId(id);
        return farmRepository.save(farm);
    }

    public void deleteFarm(String id) {
        farmRepository.deleteById(id);
    }


}
