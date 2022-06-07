package com.example.demo.services;

import com.example.demo.models.View;
import com.example.demo.repository.ViewRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class ViewService {

    final private ViewRepository viewRepository;

    public ViewService(ViewRepository viewRepository) {
        this.viewRepository = viewRepository;
    }

    public Page<View> allViews(Pageable pageable){
        return viewRepository.findAll(pageable);
    }

    public Page<View> allByUser(String id ,Pageable pageable){
        return viewRepository.findAllByUserID(id,pageable);
    }

    public Page<View> allByHouse(String id ,Pageable pageable){
        return viewRepository.findAllByHouseID(id,pageable);
    }

    public Long houseViews(String id){
        return viewRepository.countByHouseID(id);
    }

    public View addNewView(View view) throws IllegalArgumentException{
        return viewRepository.save(view);
    }

    public String removeById(String id ) throws IllegalArgumentException{
        View view = viewRepository.findById(id).orElse(null);
        if (Objects.isNull(view) ){
            throw new IllegalArgumentException("Entry not existing");
        }else{
            viewRepository.deleteById(id);
            return "Removed entry with id: "+id;
        }
    }

    public void removeAllViews(){
        viewRepository.deleteAll();
    }

    public List<String> topThreeHouses(Pageable pageable){
        List<String> supplierNames = viewRepository.findByDistinctHouseIDs(pageable);
        return supplierNames;
    }
}
