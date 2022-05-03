package com.academy.aegrofarm;

import com.academy.aegrofarm.entity.Glebe;
import com.academy.aegrofarm.entity.Production;
import com.academy.aegrofarm.repository.GlebeRepository;
import com.academy.aegrofarm.repository.ProductionRepository;
import com.academy.aegrofarm.service.ProductionService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class ProductionServiceTest {

    @Mock
    private ProductionRepository productionRepository;

    @Mock
    private GlebeRepository glebeRepository;

    @InjectMocks
    private ProductionService productionService;

    private Glebe createAValidGlebe() {

        Glebe glebe = new Glebe();
        glebe.setId("testId");
        glebe.setName("Talhão de teste");
        glebe.setArea(new BigDecimal("100"));
        glebe.setProductions(new ArrayList<>());

        return glebe;
    }

    private Production createAValidProduction() {

        Production production = new Production();
        production.setId("productionTestId");
        production.setProduction(new BigDecimal("800"));

        return production;
    }

    @Test
    void addProduction_allGood_shouldPass() {

        Glebe validGlebe = createAValidGlebe();
        Production validProduction = createAValidProduction();

        Mockito.when(glebeRepository.findById(validGlebe.getId())).thenReturn(Optional.of(validGlebe));

        Production addedProduction = productionService.addProduction(validGlebe.getId(), validProduction);

        Assert.assertEquals(addedProduction, validProduction);

    }

    @Test
    void updateProduction_allGood_shouldPass() {

        Production validProduction = createAValidProduction();
        Glebe validGlebe = createAValidGlebe();

        Mockito.when(glebeRepository.findById(validGlebe.getId())).thenReturn(Optional.of(validGlebe));
        Mockito.when(productionRepository.existsById(validProduction.getId())).thenReturn(true);
        Mockito.when(productionRepository.save(validProduction)).thenReturn(validProduction);

        Production addedProduction = productionService.updateProduction(validGlebe.getId(), validProduction.getId(), validProduction);

        Assert.assertEquals(addedProduction, validProduction);

    }

//    @Test
//    void deleteProduction_allGood_shouldPass() {
//
//        Production validProduction = createAValidProduction();
//        Glebe validGlebe = createAValidGlebe();
//
//        Mockito.when(glebeRepository.findById(validGlebe.getId())).thenReturn(Optional.of(validGlebe));
//        Mockito.when(productionRepository.existsById(validProduction.getId())).thenReturn(true);
//        boolean existsProduction = productionService.deleteProduction(validGlebe.getId(), validProduction.getId());
//
//        Assertions.assertFalse(existsProduction);
//
//    }

//    @Test
//    void calculateGlebeProductivity_allGood_shouldPass(){
//        Glebe validGlebe = createAValidGlebe();
//
//        BigDecimal productivity = glebeService.calculateGlebeProductivity(validGlebe);
//
//        Assert.assertEquals(productivity, new BigDecimal("15"));
//    }
//
//    @Test
//    void calculateGlebeProductivity_emptyProduction_shouldReturnZero(){
//        Glebe validGlebe = createAValidGlebe();
//        validGlebe.setProduction(new ArrayList<>());
//
//        BigDecimal productivity = glebeService.calculateGlebeProductivity(validGlebe);
//
//        Assert.assertEquals(productivity, BigDecimal.ZERO);
//    }

}
