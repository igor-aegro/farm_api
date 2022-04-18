package com.academy.aegrofarm.service;

import com.academy.aegrofarm.entity.Farm;
import com.academy.aegrofarm.entity.Glebe;
import com.academy.aegrofarm.exception.ApiRequestException;
import com.academy.aegrofarm.repository.FarmRepository;
import com.academy.aegrofarm.repository.GlebeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GlebeService {

    private final FarmRepository farmRepository;

    private final GlebeRepository glebeRepository;

    public void addGlebe(String id, Glebe glebe) {

        glebeRepository.insert(glebe);

        Optional<Farm> optionalFarm = farmRepository.findById(id);

        if(optionalFarm.isEmpty()){
            throw new ApiRequestException("Fazenda não encontrada para adicionar o novo talhão!");
        }

        Farm farmToAddGlebe = optionalFarm.get();

        farmToAddGlebe.getGlebes().add(glebe);

        farmRepository.save(farmToAddGlebe);
    }
}
