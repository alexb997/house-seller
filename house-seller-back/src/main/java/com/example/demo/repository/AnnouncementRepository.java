package com.example.demo.repository;

import com.example.demo.models.Announcement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnnouncementRepository extends PagingAndSortingRepository<Announcement,String> {
    Page<Announcement> findByType(String type, Pageable pageable);
//    for filtering check pipeline aggregation!!
}
