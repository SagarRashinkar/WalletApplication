package com.sagarrashinkar.controllers;

import com.sagarrashinkar.entities.Transaction;
import com.sagarrashinkar.entities.User;
import com.sagarrashinkar.payloads.ApiResponse;
import com.sagarrashinkar.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Integer id) {
        User user = this.userService.getUser(id);
        return new ResponseEntity<User>(user, HttpStatus.FOUND);
    }

    @PostMapping("/{id}/credit")
    public ResponseEntity<ApiResponse> creditAmount(@PathVariable Integer id, @RequestBody double amount) {
        User user = this.userService.creditAmount(id, amount);
        if (user != null) {
            return new ResponseEntity<>(new ApiResponse("Transaction successfully completed", true), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ApiResponse("Transaction failed due to insufficient balance", false), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/{id}/debit")
    public ResponseEntity<ApiResponse> debitAmount(@PathVariable Integer id, @RequestBody double amount) {
        User user = this.userService.debitAmount(id, amount);
        if (user != null) {
            return new ResponseEntity<>(new ApiResponse("Transaction successfully completed", true), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ApiResponse("Transaction failed due to insufficient balance", false), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}/transactions")
    public ResponseEntity<List<Transaction>> getAllTransactions(@PathVariable Integer id) {
        List<Transaction> allTransactions = this.userService.getAllTransactions(id);
        return new ResponseEntity<>(allTransactions, HttpStatus.FOUND);
    }

}