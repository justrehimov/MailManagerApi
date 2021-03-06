package com.mailmanager.repository;

import com.mailmanager.entity.ConfirmationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConfirmationTokenRepo extends JpaRepository<ConfirmationToken,Long> {
    Optional<ConfirmationToken> findConfirmationTokenByToken(String token);
}
