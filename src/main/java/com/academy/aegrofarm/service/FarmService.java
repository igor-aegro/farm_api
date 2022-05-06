package com.academy.aegrofarm.service;

import com.academy.aegrofarm.entity.Farm;
import com.academy.aegrofarm.entity.Glebe;
import com.academy.aegrofarm.exception.InvalidOperationException;
import com.academy.aegrofarm.exception.ObjectNotFoundException;
import com.academy.aegrofarm.repository.FarmRepository;
import com.academy.aegrofarm.repository.GlebeRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
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

        Farm farmToExclude = getFarmById(id);
        List<Glebe> glebes = farmToExclude.getGlebes();
        List<String> glebesIds = glebes.stream()
                                .map(Glebe::getId)
                                .collect(Collectors.toList());
        glebeRepository.deleteAllById(glebesIds);
        farmToExclude.setGlebes(new ArrayList<>());
        farmRepository.deleteById(id);
        return farmRepository.existsById(id);
    }

    public void updateProductivity(String id){
        Farm farm = getFarmById(id);
        BigDecimal productivity = calculateFarmProductivity(farm);
        farm.setProductivity(productivity);
        farmRepository.save(farm);
    }

    public BigDecimal calculateFarmProductivity(Farm farm) {
        BigDecimal farmArea = farm.getGlebes().stream()
                .map(Glebe::getArea)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        if(farmArea.compareTo(BigDecimal.ZERO) <= 0){
            throw new InvalidOperationException("Área inválida ou nula encontrada! Verifique a área dos talhões!");
        }

        BigDecimal weightedSumOfProductivities = farm.getGlebes().stream()
                                                .map(glebe -> glebe.getProductivity().multiply(glebe.getArea()))
                                                .reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal productivity = weightedSumOfProductivities.divide(farmArea, RoundingMode.HALF_UP);

        return productivity;
    }

}
