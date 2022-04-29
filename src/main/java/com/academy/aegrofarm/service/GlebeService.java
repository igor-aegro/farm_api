package com.academy.aegrofarm.service;

import com.academy.aegrofarm.entity.Farm;
import com.academy.aegrofarm.entity.Glebe;
import com.academy.aegrofarm.exception.ApiRequestException;
import com.academy.aegrofarm.repository.FarmRepository;
import com.academy.aegrofarm.repository.GlebeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GlebeService {

    private final FarmRepository farmRepository;

    private final GlebeRepository glebeRepository;

    public Glebe addGlebe(String farmId, Glebe glebe) {

        glebeRepository.insert(glebe);

        Optional<Farm> optionalFarm = farmRepository.findById(farmId);

        if(optionalFarm.isEmpty()){
            throw new ApiRequestException("Fazenda não encontrada para adicionar o novo talhão!");
        }

        Farm farmToAddGlebe = optionalFarm.get();

        farmToAddGlebe.getGlebes().add(glebe);

        farmRepository.save(farmToAddGlebe);

        return glebe;
    }

    public Glebe updateGlebe(String farmId, String glebeId, Glebe glebe) {
        if(!(farmRepository.existsById(farmId) && glebeRepository.existsById(glebeId))){
            throw new ApiRequestException("Talhão não encontrado!");
        }
        glebe.setId(glebeId);
        return glebeRepository.save(glebe);
    }

    public boolean deleteGlebe(String farmId, String glebeId) {
        if(!farmRepository.existsById(farmId)){
            throw new ApiRequestException("Essa fazenda não existe! Por favor, tente mais tarde!");
        }

        if(!glebeRepository.existsById(glebeId)){
            throw new ApiRequestException("Esse talhão não existe! Por favor, tente mais tarde!");
        }

        Farm farm = farmRepository.findById(farmId).get();
        List<Glebe> glebes = farm.getGlebes();
        glebes.removeIf(it -> it.getId().equals(glebeId));
        farm.setGlebes(glebes);
        farmRepository.save(farm);
        glebeRepository.deleteById(glebeId);

        return  glebeRepository.existsById(glebeId);
    }

//    public BigDecimal calculateGlebeProductivity(Glebe glebe){
//        List<BigDecimal> productions = glebe.getProduction();
//
//        if(productions.isEmpty()){ return BigDecimal.ZERO; }
//
//        BigDecimal totalProduction = productions.stream()
//                                    .map(Objects::requireNonNull)
//                                    .reduce(BigDecimal.ZERO, BigDecimal::add);
//
//        return totalProduction.divide(new BigDecimal(productions.size()), RoundingMode.HALF_UP);
//    }

}
