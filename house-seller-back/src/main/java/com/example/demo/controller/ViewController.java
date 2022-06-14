package com.example.demo.controller;

import com.example.demo.models.View;
import com.example.demo.services.ViewService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/views")
public class ViewController {
    final private ViewService viewService;

    public ViewController(ViewService viewService) {
        this.viewService = viewService;
    }

    @GetMapping("/")
    public ResponseEntity<Response> getAllViews(@RequestParam(defaultValue = "0") int page,
                                                @RequestParam(defaultValue = "3") int size,
                                                @RequestParam(defaultValue = "id") String sortBy) {
        try{
            List<View> views;
            Pageable paging = PageRequest.of(page, size, Sort.by(sortBy));
            Page<View> pageViews;
            pageViews = viewService.allViews(paging);
            views = pageViews.getContent();
            Response response = new Response(views,pageViews.getTotalPages(),pageViews.getTotalElements(),pageViews.getNumber());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity("Views not found",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/byHouses/topThree")
    public ResponseEntity<Response> getTopThreeHouses() {
        try{
            List<String> houseIds;
            Pageable paging = PageRequest.of(0, 3);
            houseIds = viewService.topThreeHouses(paging);;
            Response response = new Response(houseIds,0, 3L,3);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity("Views not found",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/byUser/{id}")
    public ResponseEntity<Response> getAllByUser(@PathVariable("id") String id ,
                                                 @RequestParam(defaultValue = "0") int page,
                                                 @RequestParam(defaultValue = "3") int size,
                                                 @RequestParam(defaultValue = "id") String sortBy) {
        try{
            List<View> views;
            Pageable paging = PageRequest.of(page, size, Sort.by(sortBy));
            Page<View> pageViews;
            pageViews = viewService.allByUser(id,paging);
            views = pageViews.getContent();
            Response response = new Response(views,pageViews.getTotalPages(),pageViews.getTotalElements(),pageViews.getNumber());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity("Views not found",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/byHouse/{id}")
    public ResponseEntity<Response> getAllByHouse(@PathVariable("id") String id ,
                                                  @RequestParam(defaultValue = "0") int page,
                                                  @RequestParam(defaultValue = "3") int size,
                                                  @RequestParam(defaultValue = "id") String sortBy) {
        try{
            List<View> views;
            Pageable paging = PageRequest.of(page, size, Sort.by(sortBy));
            Page<View> pageViews;
            pageViews = viewService.allByHouse(id,paging);
            views = pageViews.getContent();
            Response response = new Response(views,pageViews.getTotalPages(),pageViews.getTotalElements(),pageViews.getNumber());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity("Views not found",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/house/{id}")
    public ResponseEntity<Long> getViewsCount(@PathVariable("id") String id ,
                                                  @RequestParam(defaultValue = "0") int page,
                                                  @RequestParam(defaultValue = "3") int size) {
        try{
            Long count = viewService.houseViews(id);
            return new ResponseEntity<>(count, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity("Views not found",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<View> registerView(@RequestBody View view) {
        try{
            View newView= viewService.addNewView(view);
            return new ResponseEntity<>(newView, HttpStatus.CREATED);
        }catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteView(@PathVariable("id") String id) {
        try{
            viewService.removeById(id);
            return new ResponseEntity<>("Removed view with id:" ,HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/remove")
    public ResponseEntity<String> all() {
        viewService.removeAllViews();
        return new ResponseEntity<>("Removed all view entryes",HttpStatus.OK);
    }
}
