package com.example.demo.repository;

import com.example.demo.models.House;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HouseRepository extends PagingAndSortingRepository<House,String> {
    Page<House> findByReductionGreaterThanOrderByReductionDesc(int greater,Pageable pageable);
    Page<House> findByIdIn(List<String> ids, Pageable pageable);
    Page<House> findAllByStatusMatchesRegexAndDimensionsMatchesRegexAndAddressMatchesRegexAndOwnerMatchesRegexAndNumberAndPrice(String status,String dimentions,String address,String owner,int number, int price,Pageable pageable);
    Page<House> findAllByStatusMatchesRegexAndDimensionsMatchesRegexAndAddressMatchesRegexAndOwnerMatchesRegexAndPrice(String status,String dimentions,String address,String owner, int price,Pageable pageable);
    Page<House> findAllByStatusMatchesRegexAndDimensionsMatchesRegexAndAddressMatchesRegexAndOwnerMatchesRegexAndNumber(String status,String dimentions,String address,String owner,int number, Pageable pageable);
    Page<House> findAllByStatusMatchesRegexAndDimensionsMatchesRegexAndAddressMatchesRegexAndOwnerMatchesRegex(String status,String dimentions,String address,String owner, Pageable pageable);

}
