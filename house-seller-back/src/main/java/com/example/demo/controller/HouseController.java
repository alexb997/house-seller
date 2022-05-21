package com.example.demo.controller;

import com.example.demo.models.House;
import com.example.demo.services.HouseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/houses")
public class HouseController {

    private final HouseService houseService;

    public HouseController(HouseService houseService){this.houseService = houseService;}


    @PostMapping("/add")
    public ResponseEntity<House> addHouse(@RequestBody House house) {
            return new ResponseEntity<>(house,HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<Response> allHouses(@RequestParam(defaultValue = "0") int page,
                                               @RequestParam(defaultValue = "5") int size){
        try{
            List<House> houses;
            Pageable paging = PageRequest.of(page, size);
            Page<House> pageHouses;
            pageHouses = houseService.findAll(paging);
            houses = pageHouses.getContent();
            Response response = new Response(houses,pageHouses.getTotalPages(),pageHouses.getTotalElements(),pageHouses.getNumber());
            return new ResponseEntity<>(response,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity("No houses found",HttpStatus.NOT_FOUND);
        }
    }

}
