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

    public void addFarm(Farm farm){
        farmRepository.insert(farm);
    }

    public List<Farm> getAllFarms() {
        return farmRepository.findAll();
    }

    public Farm getFarmByName(String name) {
        return farmRepository.findByName(name);
    }

    public Farm updateFarm(String id, @NotNull Farm farm) {
        farm.setId(id);
        return farmRepository.save(farm);
    }

    public void deleteFarm(String id) {
        farmRepository.deleteById(id);
    }

//    public Optional<Farm> getFarmById(String id) {
//        return  farmRepository.findById(id);
//    }
}
