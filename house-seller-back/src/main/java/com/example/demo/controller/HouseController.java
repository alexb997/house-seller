package com.example.demo.controller;

import com.example.demo.services.HouseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/houses")
public class HouseController {

    private final HouseService houseService;

    public HouseController(HouseService houseService){this.houseService = houseService;}

    @GetMapping("/all")
    public ResponseEntity<String> getAllHouses() {
        return  new ResponseEntity<>("You've called house controller get all \n"+ houseService.findAll(), HttpStatus.OK);
    }
}
