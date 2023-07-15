package com.sagarrashinkar.repository;

import com.sagarrashinkar.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUserName(String username);

}
