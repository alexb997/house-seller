package com.example.demo.repository;

import com.example.demo.models.Appartment;;
import com.example.demo.models.House;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppartmentsRepository extends PagingAndSortingRepository<Appartment,String> {
    Page<Appartment> findByReductionGreaterThanOrderByReductionDesc(int greater, Pageable pageable);
    Page<Appartment> findByIdIn(List<String> ids, Pageable pageable);
}
