package com.example.demo.services;

import com.example.demo.models.Appartment;
import com.example.demo.repository.AppartmentsRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class AppartmentsService {

    private final AppartmentsRepository appartmentsRepository;

    public AppartmentsService(AppartmentsRepository appartmentsRepository)
    {this.appartmentsRepository=appartmentsRepository;}

    public Page<Appartment> findAll(Pageable pageable){
        return appartmentsRepository.findAll(pageable);
    }

    public Page<Appartment> findByIDIn(List ids, Pageable pageable){
        return appartmentsRepository.findByIdIn(ids,pageable);
    }

    public Page<Appartment> findAllReduced(Pageable pageable){
        return appartmentsRepository.findByReductionGreaterThanOrderByReductionDesc(0,pageable);
    }

    public Appartment addAppartment(Appartment appartment) throws IllegalArgumentException{
        return appartmentsRepository.save(appartment);
    }

    public void removeAllAppartments(){
        appartmentsRepository.deleteAll();
    }

    public void removeAppartmentBy(String id ) throws IllegalArgumentException{
        Appartment appartment = appartmentsRepository.findById(id).orElse(null);
        if (Objects.isNull(appartment) ){
            throw new IllegalArgumentException("Entry not existing");
        }else{
            appartmentsRepository.deleteById(id);
        }
    }

    public Appartment getByID(String id ) throws IllegalArgumentException{
        Appartment appartment = appartmentsRepository.findById(id).orElse(null);
        if (Objects.isNull(appartment) ){
            throw new IllegalArgumentException("Entry not existing");
        }else{
            return appartment;
        }
    }
}
