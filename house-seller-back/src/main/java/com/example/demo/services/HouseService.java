package com.example.demo.services;

import org.springframework.stereotype.Service;

@Service
public class HouseService {
    public String findAll(){
        return "You've called house service find all";
    }
}
