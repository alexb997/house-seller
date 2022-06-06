package com.example.demo.repository;

import com.example.demo.models.View;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ViewRepository extends PagingAndSortingRepository<View,String> {
    Page<View> findAllByUserID(String id, Pageable pageable);
    Page<View> findAllByHouseID(String id, Pageable pageable);
    Long countByHouseID(String id);
    @Query(value="Select houseID from views group by houseID")
    List<String> topThreeHouses();
}
