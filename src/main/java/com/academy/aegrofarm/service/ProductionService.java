package com.academy.aegrofarm.service;

import com.academy.aegrofarm.entity.Glebe;
import com.academy.aegrofarm.entity.Production;
import com.academy.aegrofarm.exception.ApiRequestException;
import com.academy.aegrofarm.repository.GlebeRepository;
import com.academy.aegrofarm.repository.ProductionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductionService {

    private final GlebeRepository glebeRepository;
    private final ProductionRepository productionRepository;
    public void addProduction(String glebeId, Production production) {
        productionRepository.insert(production);

        Optional<Glebe> optionalGlebe = glebeRepository.findById(glebeId);

        if(optionalGlebe.isEmpty()){
            throw new ApiRequestException("Talhão não encontrado!");
        }

        Glebe glebeToAddProduction = optionalGlebe.get();

        glebeToAddProduction.getProductions().add(production);

        glebeRepository.save(glebeToAddProduction);

    }

    public Production updateProduction(String glebeId, String productionId, Production production) {
        if(!productionRepository.existsById(productionId)){
            throw new ApiRequestException("Produção não encontrada!");
        }
        production.setId(productionId);
        return productionRepository.save(production);
    }

    public boolean deleteProduction(String glebeId, String productionId) {
        if(!productionRepository.existsById(productionId)){
            throw new ApiRequestException("Produção não existe!");
        }

        Glebe glebe = glebeRepository.findById(glebeId).get();
        List<Production> productions = glebe.getProductions();
        productions.removeIf(production -> production.getId().equals(productionId));
        glebe.setProductions(productions);
        glebeRepository.save(glebe);
        productionRepository.deleteById(productionId);

        return  productionRepository.existsById(productionId);
    }

}
