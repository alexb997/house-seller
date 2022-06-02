package com.example.demo.repository;

import com.example.demo.models.View;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ViewRepository extends PagingAndSortingRepository<View,String> {
    Page<View> findAllByUserID(String id, Pageable pageable);
    Page<View> findAllByHouseID(String id, Pageable pageable);
}
