package com.example.demo.services;

import com.example.demo.models.House;
import com.example.demo.models.User;
import com.example.demo.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;


@Service
public class UserService {

    final private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Page<User> allUsers(Pageable pageable){
        return userRepository.findAll(pageable);
    }

    public User addNewUser(User user) throws IllegalArgumentException{
        return userRepository.save(user);
    }

    public String removeById(String id ) throws IllegalArgumentException{
        User user = userRepository.findById(id).orElse(null);
        if (Objects.isNull(user) ){
            throw new IllegalArgumentException("Entry not existing");
        }else{
            userRepository.deleteById(id);
            return "Removed entry with id: "+id;
        }
    }
}