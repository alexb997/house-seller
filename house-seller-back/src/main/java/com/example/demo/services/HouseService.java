package com.example.demo.services;

import com.example.demo.models.House;
import com.example.demo.repository.HouseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HouseService {

    private final HouseRepository houseRepository;

    public HouseService(HouseRepository houseRepository){this.houseRepository=houseRepository;}

    public Page<House> findAll(Pageable pageable){
        return houseRepository.findAll(pageable);
    }

    public House addHouse(House house) throws IllegalArgumentException{
        return houseRepository.save(house);
    }

    public Optional<House> editHouse(String id, House house) throws IllegalArgumentException{
        return houseRepository.findById(id)
                .map(oldHouse -> {
                    House updated = oldHouse.updateWith(house);
                    updated.setId(id);
                    return houseRepository.save(updated);
                });
    }
}
