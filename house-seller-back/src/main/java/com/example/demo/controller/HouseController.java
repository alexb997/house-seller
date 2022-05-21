package com.example.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/houses")
public class HouseController {

    @GetMapping("/all")
    public ResponseEntity<String> getAllHouses() {
        return  new ResponseEntity<>("You've called house controller get all", HttpStatus.OK);
    }
}
