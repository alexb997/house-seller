package com.example.demo.controller;

import com.example.demo.models.Appartment;
import com.example.demo.services.AppartmentsService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/appartments")
public class AppartmentsController {

    private final AppartmentsService appartmentsService;

    public AppartmentsController(AppartmentsService appartmentsService){this.appartmentsService = appartmentsService;}


    @PostMapping("/add")
    public ResponseEntity<Appartment> addAppartment(@RequestBody Appartment appartment) {
        try{
            Appartment newAppartment= appartmentsService.addAppartment(appartment);
            return new ResponseEntity<>(newAppartment, HttpStatus.CREATED);
        }catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<Response> all(@RequestParam(defaultValue = "0") int page,
                                        @RequestParam(defaultValue = "5") int size,
                                        @RequestParam(defaultValue = "id") String sortBy){
        try{
            List<Appartment> appartments;
            Pageable paging = PageRequest.of(page, size, Sort.by(sortBy));
            Page<Appartment> pageAppartments;
            pageAppartments = appartmentsService.findAll(paging);
            appartments = pageAppartments.getContent();
            Response response = new Response(appartments,pageAppartments.getTotalPages(),pageAppartments.getTotalElements(),pageAppartments.getNumber());
            return new ResponseEntity<>(response,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity("No appartments found",HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/byReductions")
    public ResponseEntity<Response> allOrderedByReduction(@RequestParam(defaultValue = "0") int page,
                                                          @RequestParam(defaultValue = "5") int size,
                                                          @RequestParam(defaultValue = "id") String sortBy){
        try{
            List<Appartment> appartments;
            Pageable paging = PageRequest.of(page, size, Sort.by(sortBy));
            Page<Appartment> pageAppartments;
            pageAppartments = appartmentsService.findAllReduced(paging);
            appartments = pageAppartments.getContent();
            Response response = new Response(appartments,pageAppartments.getTotalPages(),pageAppartments.getTotalElements(),pageAppartments.getNumber());
            return new ResponseEntity<>(response,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity("No appartments found",HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> byID(@PathVariable("id") String id){
        try{
            return new ResponseEntity<>(appartmentsService.getByID(id),HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/ids")
    public ResponseEntity<?> byID(@RequestParam(required = false) List<String> ids, @PathVariable("id") String id){
        try{
            List<Appartment> appartments;
            Pageable paging = PageRequest.of(0, 3);
            Page<Appartment> pageAppartments;
            pageAppartments = appartmentsService.findByIDIn(ids,paging);
            appartments = pageAppartments.getContent();
            Response response = new Response(appartments,pageAppartments.getTotalPages(),pageAppartments.getTotalElements(),pageAppartments.getNumber());
            return new ResponseEntity<>(response,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity("Appartments not found",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/remove")
    public ResponseEntity<String> all() {
        appartmentsService.removeAllAppartments();
        return new ResponseEntity<>("removed all appartment entryes",HttpStatus.OK);
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<String> specificID(@PathVariable("id") String id) {
        try{
            appartmentsService.removeAppartmentBy(id);
            return new ResponseEntity<>("Removed appartment with id:"+ id,HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
}
