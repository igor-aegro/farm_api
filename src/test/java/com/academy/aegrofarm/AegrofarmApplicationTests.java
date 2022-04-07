package com.academy.aegrofarm;

import com.academy.aegrofarm.entity.Farm;
import com.academy.aegrofarm.entity.Glebe;
import com.academy.aegrofarm.repository.FarmRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class AegrofarmApplicationTests {

	@Mock
	private FarmRepository farmRepository;

	private Farm createAValidFarm() {

		Farm farm = new Farm();
		farm.setId("testId");
		farm.setName("Fazenda de teste");
		farm.setArea(new BigDecimal("0520.607"));
		farm.setGlebes(new ArrayList<>());

		return farm;
	}

	@Test
	void addFarm_allGood_shouldPass() {

		Farm farm = createAValidFarm();

		Answer<Farm> repositoryAnswer = invocationOnMock -> {
			Farm newFarm = createAValidFarm();
			newFarm.setId("wrongId");
			return newFarm;
		};
		Mockito.when(farmRepository.save(farm)).thenAnswer(repositoryAnswer);

		Farm addedFarm = farmRepository.save(farm);

		Assert.assertEquals(addedFarm.getId(), farm.getId());

	}

}
