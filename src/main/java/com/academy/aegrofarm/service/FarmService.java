package com.academy.aegrofarm.service;

import com.academy.aegrofarm.entity.Farm;
import com.academy.aegrofarm.entity.Glebe;
import com.academy.aegrofarm.exception.ObjectNotFoundException;
import com.academy.aegrofarm.repository.FarmRepository;
import com.academy.aegrofarm.repository.GlebeRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FarmService {

    private final FarmRepository farmRepository;

    private final GlebeRepository glebeRepository;

    public FarmService(FarmRepository farmRepository, GlebeRepository glebeRepository) {
        this.farmRepository = farmRepository;
        this.glebeRepository = glebeRepository;
    }

    public List<Farm> getFarms() {
        return farmRepository.findAll();
    }

    public Farm getFarmById(String id) {
        Optional<Farm> farm = farmRepository.findById(id);
        if(farm.isEmpty()) {
            throw new ObjectNotFoundException("Fazenda não encontrada!");
        }
        return farm.get();
    }

    public Farm addFarm(Farm farm){
        return farmRepository.insert(farm);
    }

    public Farm updateFarm(String id, Farm farm) {
        farm.setId(id);
        return farmRepository.save(farm);
    }

    public boolean deleteFarm(String id) {

        Optional<Farm> farmOptional = farmRepository.findById(id);

        if(!farmOptional.isPresent()) {
            throw new ObjectNotFoundException("Fazenda não encontrada!");
        }

        Farm farmToExclude = farmOptional.get();
        List<Glebe> glebes = farmToExclude.getGlebes();
        List<String> glebesIds = glebes.stream()
                                .map(Glebe::getId)
                                .collect(Collectors.toList());
        glebeRepository.deleteAllById(glebesIds);
        farmToExclude.setGlebes(new ArrayList<>());
        farmRepository.deleteById(id);
        return farmRepository.existsById(id);
    }

    public BigDecimal calculateFarmProductivity(String id) {
        Farm farm = farmRepository.findById(id).get();
        BigDecimal productivity = farm.getGlebes().stream()
                .map(Glebe::getProductivity)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        farm.setProductivity(productivity);
        farmRepository.save(farm);

        return productivity;
    }

}
