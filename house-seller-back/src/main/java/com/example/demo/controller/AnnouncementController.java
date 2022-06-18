package com.example.demo.controller;

import com.example.demo.models.Announcement;
import com.example.demo.services.AnnouncementService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/announcements")
public class AnnouncementController {
    private final AnnouncementService announcementService;

    public AnnouncementController(AnnouncementService announcementService){this.announcementService = announcementService;}

    @GetMapping("/{type}")
    public ResponseEntity<Response> getAllAnnouncementsByType(@PathVariable("type") String type,
                                                @RequestParam(defaultValue = "0") int page,
                                                @RequestParam(defaultValue = "3") int size) {
        try{
            List<Announcement> announcements;
            Pageable paging = PageRequest.of(page, size);
            Page<Announcement> pageAnnouncements;
            pageAnnouncements = announcementService.allByType(type,paging);
            announcements = pageAnnouncements.getContent();
            Response response = new Response(announcements,pageAnnouncements.getTotalPages(),pageAnnouncements.getTotalElements(),pageAnnouncements.getNumber());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity("Announcements not found",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<Announcement> registerAnnouncement(@RequestBody Announcement announcement) {
        System.out.println("Calling controller register announcement");
        try{
            Announcement newAnnouncement= announcementService.addNewAnnouncement(announcement);
            return new ResponseEntity<>(newAnnouncement, HttpStatus.CREATED);
        }catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteAnnouncement(@PathVariable("id") String id) {
        try{
            announcementService.removeById(id);
            return new ResponseEntity<>("Removed announcement with id:",HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
}
