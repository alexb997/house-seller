package com.example.demo.services;

import com.example.demo.models.House;
import com.example.demo.repository.HouseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;
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

    public String removeAllHouses(){
        houseRepository.deleteAll();
        return "Removed all entries";
    }

    public String removeSpecificHouse(String id ) throws IllegalArgumentException{
        House house = houseRepository.findById(id).orElse(null);
        if (Objects.isNull(house) ){
            throw new IllegalArgumentException("Entry not existing");
        }else{
            houseRepository.deleteById(id);
            return "Removed entry with id: "+id;
        }
    }

    public House getByID(String id ) throws IllegalArgumentException{
        House house = houseRepository.findById(id).orElse(null);
        if (Objects.isNull(house) ){
            throw new IllegalArgumentException("Entry not existing");
        }else{
            return house;
        }
    }
}
