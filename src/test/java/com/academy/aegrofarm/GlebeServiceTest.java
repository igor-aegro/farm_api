package com.academy.aegrofarm;

import com.academy.aegrofarm.entity.Farm;
import com.academy.aegrofarm.entity.Glebe;
import com.academy.aegrofarm.repository.FarmRepository;
import com.academy.aegrofarm.repository.GlebeRepository;
import com.academy.aegrofarm.service.GlebeService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class GlebeServiceTest {

    @Mock
    private FarmRepository farmRepository;

    @Mock
    private GlebeRepository glebeRepository;

    @InjectMocks
    private GlebeService glebeService;

    private Farm createAValidFarm() {

        Farm farm = new Farm();
        farm.setId("testId");
        farm.setName("Fazenda de teste");
        farm.setGlebes(new ArrayList<>());

        return farm;
    }

    private Glebe createAValidGlebe() {

        Glebe glebe = new Glebe();
        glebe.setId("glebeTestId");
        glebe.setName("Talhão de teste");
        glebe.setArea(new BigDecimal("340.0056"));
        List<BigDecimal> production = new ArrayList<>();
        production.add(new BigDecimal("10"));
        production.add(new BigDecimal("20"));
        glebe.setProduction(production);

        return glebe;
    }

    @Test
    void addGlebe_allGood_shouldPass() {

        Glebe validGlebe = createAValidGlebe();
        Farm validFarm = createAValidFarm();

        Mockito.when(glebeRepository.insert(validGlebe)).thenReturn(validGlebe);

        Glebe addedGlebe = glebeService.addGlebe(validFarm.getId(), validGlebe);

        Assert.assertEquals(addedGlebe, validGlebe);

    }

    @Test
    void updateGlebe_allGood_shouldPass() {

        Glebe validGlebe = createAValidGlebe();
        Farm validFarm = createAValidFarm();

        Mockito.when(glebeRepository.save(validGlebe)).thenReturn(validGlebe);

        Glebe addedGlebe = glebeService.updateGlebe(validFarm.getId(), validGlebe.getId(), validGlebe);

        Assert.assertEquals(addedGlebe, validGlebe);

    }

    @Test
    void deleteGlebe_allGood_shouldPass() {

        Glebe validGlebe = createAValidGlebe();
        Farm validFarm = createAValidFarm();

        glebeService.deleteGlebe(validFarm.getId(), validGlebe.getId());

        Assert.assertFalse(glebeRepository.existsById(validGlebe.getId()));

    }

    @Test
    void calculateGlebeProductivity_allGood_shouldPass(){
        Glebe validGlebe = createAValidGlebe();

        BigDecimal productivity = glebeService.calculateGlebeProductivity(validGlebe);

        Assert.assertEquals(productivity, new BigDecimal("15"));
    }

}
