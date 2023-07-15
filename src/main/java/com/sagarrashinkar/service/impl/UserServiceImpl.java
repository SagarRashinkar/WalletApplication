package com.sagarrashinkar.service.impl;

import com.sagarrashinkar.entities.Transaction;
import com.sagarrashinkar.entities.User;
import com.sagarrashinkar.exceptions.ResourceNotFoundException;
import com.sagarrashinkar.payloads.ApiResponse;
import com.sagarrashinkar.repository.TransactionRepository;
import com.sagarrashinkar.repository.UserRepository;
import com.sagarrashinkar.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public User getUser(Integer id) {
        return this.userRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("User", "id", id));
    }

    @Override
    public User creditAmount(Integer id, double amount) {
        User user = this.userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
        user.setBalance(user.getBalance() + amount);
        userRepository.save(user);
        Transaction transaction = new Transaction();
        transaction.setUser(user);
        transaction.setTimeStamp(LocalDateTime.now());
        transaction.setAmount(amount);
        transaction.setTransactionType("credit");
        transactionRepository.save(transaction);
        return user;
    }

    @Override
    public User debitAmount(Integer id, double amount) {
        User user = this.userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
        if(user.getBalance() > 0){
            if (user.getBalance() >= amount){
                double newBalance = user.getBalance() - amount;
                user.setBalance(newBalance);
                userRepository.save(user);
                Transaction transaction = new Transaction();
                transaction.setUser(user);
                transaction.setTimeStamp(LocalDateTime.now());
                transaction.setAmount(amount);
                transaction.setTransactionType("debit");
                transactionRepository.save(transaction);
                return user;
            }else {
                return null;
            }
        }
        return null;
    }

    @Override
    public List<Transaction> getAllTransactions(Integer id) {
        User user = this.userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
        List<Transaction> transactions = this.transactionRepository.findByUserOrderByTimeStampDesc(user);
        return transactions;
    }

}
