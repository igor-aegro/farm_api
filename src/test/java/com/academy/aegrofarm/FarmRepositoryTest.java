package com.academy.aegrofarm;

import com.academy.aegrofarm.entity.Farm;
import com.academy.aegrofarm.repository.FarmRepository;
import com.academy.aegrofarm.service.FarmService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

@ExtendWith(MockitoExtension.class)
class FarmServiceTest {

    @Mock
    private FarmRepository farmRepository;

    @InjectMocks
    private FarmService farmService;

    private Farm createAValidFarm() {

        Farm farm = new Farm();
        farm.setId("testId");
        farm.setName("Fazenda de teste");
        farm.setGlebes(new ArrayList<>());

        return farm;
    }

    @Test
    void addFarm_allGood_shouldPass() {

        Farm validFarm = createAValidFarm();

        Mockito.when(farmRepository.insert(validFarm)).thenReturn(validFarm);

        Farm addedFarm = farmService.addFarm(validFarm);

        Assert.assertEquals(addedFarm.getId(), validFarm.getId());

    }

}
