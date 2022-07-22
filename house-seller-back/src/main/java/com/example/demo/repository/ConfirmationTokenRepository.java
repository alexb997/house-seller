package com.example.demo.repository;

import com.example.demo.models.ConfirmationToken;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface ConfirmationTokenRepository extends PagingAndSortingRepository<ConfirmationToken,String> {

    Optional<ConfirmationToken> findByToken(String token);
}
