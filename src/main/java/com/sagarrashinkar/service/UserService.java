package com.sagarrashinkar.service;

import com.sagarrashinkar.entities.Transaction;
import com.sagarrashinkar.entities.User;

import java.util.List;

public interface UserService {

    public User getUser(Integer id);


    User creditAmount(Integer id, double amount);

    User debitAmount(Integer id, double amount);

    List<Transaction> getAllTransactions(Integer id);

}
