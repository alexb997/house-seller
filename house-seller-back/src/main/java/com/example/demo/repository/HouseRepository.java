package com.example.demo.repository;

import com.example.demo.models.House;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HouseRepository extends PagingAndSortingRepository<House,String> {
    Page<House> findByReductionGreaterThanOrderByReductionDesc(int greater,Pageable pageable);
}
