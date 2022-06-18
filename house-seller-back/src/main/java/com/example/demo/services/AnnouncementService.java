package com.example.demo.services;

import com.example.demo.models.Announcement;
import com.example.demo.repository.AnnouncementRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AnnouncementService {

    final private AnnouncementRepository announcementRepository;

    public AnnouncementService(AnnouncementRepository announcementRepository) {
        this.announcementRepository = announcementRepository;
    }

    public Page<Announcement> allByType(String type,Pageable pageable){
        return announcementRepository.findByType(type,pageable);
    }

    public Announcement addNewAnnouncement(Announcement announcement) throws IllegalArgumentException{
        return announcementRepository.save(announcement);
    }

    public String removeById(String id ) throws IllegalArgumentException{
        Announcement announcement = announcementRepository.findById(id).orElse(null);
        if (Objects.isNull(announcement) ){
            throw new IllegalArgumentException("Entry not existing");
        }else{
            announcementRepository.deleteById(id);
            return "Removed entry with id: "+id;
        }
    }
}
