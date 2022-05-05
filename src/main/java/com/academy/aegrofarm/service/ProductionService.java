package com.academy.aegrofarm.service;

import com.academy.aegrofarm.entity.Glebe;
import com.academy.aegrofarm.entity.Production;
import com.academy.aegrofarm.exception.ObjectNotFoundException;
import com.academy.aegrofarm.repository.GlebeRepository;
import com.academy.aegrofarm.repository.ProductionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductionService {

    private final GlebeRepository glebeRepository;
    private final ProductionRepository productionRepository;

    public List<Production> getProductionsFromGlebe(String glebeId) {
        return glebeRepository.findById(glebeId).get().getProductions();
    }

    public Production getProductionById(String id) {
        return productionRepository.findById(id).get();
    }

    public Production addProduction(String glebeId, Production production) {
        productionRepository.insert(production);

        Optional<Glebe> optionalGlebe = glebeRepository.findById(glebeId);

        if(optionalGlebe.isEmpty()){
            throw new ObjectNotFoundException("Talhão não encontrado!");
        }

        Glebe glebeToAddProduction = optionalGlebe.get();

        glebeToAddProduction.getProductions().add(production);

        glebeRepository.save(glebeToAddProduction);

        calculateGlebeProductivity(glebeId);

        return production;
    }

    public Production updateProduction(String glebeId, String productionId, Production production) {
        if(!productionRepository.existsById(productionId)){
            throw new ObjectNotFoundException("Produção não encontrada!");
        }
        production.setId(productionId);
        Production updatedProduction = productionRepository.save(production);
        calculateGlebeProductivity(glebeId);
        return updatedProduction;
    }

    public boolean deleteProduction(String glebeId, String productionId) {
        Glebe glebe = glebeRepository.findById(glebeId).get();
        List<Production> productions = glebe.getProductions();
        productions.removeIf(production -> production.getId().equals(productionId));
        glebe.setProductions(productions);
        glebeRepository.save(glebe);
        calculateGlebeProductivity(glebeId);
        productionRepository.deleteById(productionId);

        return  productionRepository.existsById(productionId);
    }

    public BigDecimal calculateGlebeProductivity(String glebeId){

        Glebe glebe = glebeRepository.findById(glebeId).get();
        List<Production> productions = glebe.getProductions();
        BigDecimal productivity;

        if(productions.isEmpty()){
            productivity = BigDecimal.ZERO;
        } else {
            BigDecimal totalProduction = productions.stream()
                    .map(Production::getProduction)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);

            productivity = totalProduction.divide(glebe.getArea(), RoundingMode.HALF_UP);
        }

        glebe.setProductivity(productivity);

        glebeRepository.save(glebe);

        return productivity;

    }

}
