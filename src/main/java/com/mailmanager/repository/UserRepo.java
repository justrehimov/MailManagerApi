package com.mailmanager.repository;

import com.mailmanager.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {

    @Query("select u from User u where u.email=:username")
    Optional<User> findByUsername(String username);

    @Modifying
    @Query("update User u set u.active=:enabled where u.id=:id")
    void setActive(Long id, boolean enabled);
}
