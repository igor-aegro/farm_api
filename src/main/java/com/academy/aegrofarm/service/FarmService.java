package com.academy.aegrofarm.service;

import com.academy.aegrofarm.entity.Farm;
import com.academy.aegrofarm.entity.Glebe;
import com.academy.aegrofarm.repository.FarmRepository;
import com.academy.aegrofarm.repository.GlebeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FarmService {

    private final FarmRepository farmRepository;

    private final GlebeRepository glebeRepository;

    public Farm addFarm(Farm farm){
        return farmRepository.insert(farm);
    }

    public Farm updateFarm(String id, Farm farm) {
        farm.setId(id);
        return farmRepository.save(farm);
    }

    public boolean deleteFarm(String id) {
        Farm farmToExclude = farmRepository.findById(id).get();
        List<Glebe> glebes = farmToExclude.getGlebes();
        List<String> glebesIds = glebes.stream()
                                .map(Glebe::getId)
                                .collect(Collectors.toList());
        glebeRepository.deleteAllById(glebesIds);
        farmToExclude.setGlebes(new ArrayList<>());
        farmRepository.deleteById(id);
        return farmRepository.existsById(id);
    }

}
