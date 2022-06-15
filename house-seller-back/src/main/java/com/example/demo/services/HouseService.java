package com.example.demo.services;

import com.example.demo.models.House;
import com.example.demo.repository.HouseRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Service
public class HouseService {

    private final HouseRepository houseRepository;

    public HouseService(HouseRepository houseRepository){this.houseRepository=houseRepository;}

    public Page<House> findAll(Pageable pageable){
        return houseRepository.findAll(pageable);
    }

    public Page<House> findByIDIn(List ids,Pageable pageable){
        return houseRepository.findByIdIn(ids,pageable);
    }

    public Page<House> findAllReduced(Pageable pageable){
        return houseRepository.findByReductionGreaterThanOrderByReductionDesc(0,pageable);
    }

    public Page<House> findByFilters(Map<String,String> filters, Pageable pageable){
        String status= StringUtils.EMPTY;
        String dimensions=StringUtils.EMPTY;
        String address=StringUtils.EMPTY;
        String owner=StringUtils.EMPTY;
        int price=0;
        int number=0;
        for (String filter : filters.keySet()) {
            if(Objects.equals(filter, "status")){
                status=filters.get(filter);
            }
            if(Objects.equals(filter, "dimensions")){
                dimensions=filters.get(filter);
            }
            if(Objects.equals(filter, "address")){
                address=filters.get(filter);
            }
            if(Objects.equals(filter, "owner")){
                owner=filters.get(filter);
            }
            if(Objects.equals(filter, "price")){
                price=Integer.parseInt(filters.get(filter));
            }
            if(Objects.equals(filter, "number")){
                number=Integer.parseInt(filters.get(filter));
            }
        }
        if(number!=0 && price!=0) {
            return houseRepository.findAllByStatusMatchesRegexAndDimensionsMatchesRegexAndAddressMatchesRegexAndOwnerMatchesRegexAndNumberAndPrice
                    (status,dimensions,address,owner,number,price,pageable);

        }
        if(price!=0){
            return houseRepository.findAllByStatusMatchesRegexAndDimensionsMatchesRegexAndAddressMatchesRegexAndOwnerMatchesRegexAndPrice
                    (status,dimensions,address,owner,price,pageable);
        }
        if(number!=0){
            return houseRepository.findAllByStatusMatchesRegexAndDimensionsMatchesRegexAndAddressMatchesRegexAndOwnerMatchesRegexAndNumber
                    (status,dimensions,address,owner,number,pageable);
        }
        return houseRepository.findAllByStatusMatchesRegexAndDimensionsMatchesRegexAndAddressMatchesRegexAndOwnerMatchesRegex
                (status,dimensions,address,owner,pageable);
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

    public void removeAllHouses(){
        houseRepository.deleteAll();
    }

    public void removeHouseBy(String id ) throws IllegalArgumentException{
        House house = houseRepository.findById(id).orElse(null);
        if (Objects.isNull(house) ){
            throw new IllegalArgumentException("Entry not existing");
        }else{
            houseRepository.deleteById(id);
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
