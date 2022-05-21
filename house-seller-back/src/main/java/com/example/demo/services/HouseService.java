package com.example.demo.services;

import com.example.demo.models.House;
import com.example.demo.repository.HouseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class HouseService {

    private final HouseRepository houseRepository;

    public HouseService(HouseRepository houseRepository){this.houseRepository=houseRepository;}

    public Page<House> findAll(Pageable pageable){
        return houseRepository.findAll(pageable);
    }

    public House addHouse(House house){
        return house;
    }
}
