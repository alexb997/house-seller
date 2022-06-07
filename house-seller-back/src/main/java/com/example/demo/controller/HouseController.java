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
import java.util.Optional;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/houses")
public class HouseController {

    private final HouseService houseService;

    public HouseController(HouseService houseService){this.houseService = houseService;}


    @PostMapping("/add")
    public ResponseEntity<House> addHouse(@RequestBody House house) {
        try{
            House newHouse= houseService.addHouse(house);
            return new ResponseEntity<>(newHouse,HttpStatus.CREATED);
        }catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<Response> all(@RequestParam(defaultValue = "0") int page,
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

    @GetMapping("/byReductions")
    public ResponseEntity<Response> allOrderedByReduction(@RequestParam(defaultValue = "0") int page,
                                        @RequestParam(defaultValue = "5") int size){
        try{
            List<House> houses;
            Pageable paging = PageRequest.of(page, size);
            Page<House> pageHouses;
            pageHouses = houseService.findAllReduced(paging);
            houses = pageHouses.getContent();
            Response response = new Response(houses,pageHouses.getTotalPages(),pageHouses.getTotalElements(),pageHouses.getNumber());
            return new ResponseEntity<>(response,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity("No houses found",HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> byID(@PathVariable("id") String id){
        try{
            return new ResponseEntity<>(houseService.getByID(id),HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<House> editHouse(@PathVariable("id") String id, @RequestBody House house) {
        Optional<House> updated = houseService.editHouse(id,house);
        return updated.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/remove")
    public ResponseEntity<String> all() {
        houseService.removeAllHouses();
        return new ResponseEntity<>("removed all house entryes",HttpStatus.OK);
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<String> specificID(@PathVariable("id") String id) {
        try{
            houseService.removeHouseBy(id);
            return new ResponseEntity<>("Removed house with id:"+ id,HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
}
