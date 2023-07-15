package com.sagarrashinkar.repository;

import com.sagarrashinkar.entities.Transaction;
import com.sagarrashinkar.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

    List<Transaction> findByUserOrderByTimeStampDesc(User user);

}
