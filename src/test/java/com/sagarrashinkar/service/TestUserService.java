package com.sagarrashinkar.service;

import com.sagarrashinkar.entities.Transaction;
import com.sagarrashinkar.entities.User;
import com.sagarrashinkar.repository.UserRepository;
import com.sagarrashinkar.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class TestUserService {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    User user;
    Transaction t1, t2;
    List<Transaction> transactions = new ArrayList<>();

    @BeforeEach
    public void init(){
        t1 = new Transaction(1, user, LocalDateTime.now(), 1200, "debit");
        t2 = new Transaction(1, user, LocalDateTime.now(), 1250, "credit");
        transactions.add(t1);
        transactions.add(t2);
    }

    @Test
    public void testGetUser(){
        user = new User(1, "sagar123", "Sagar@123", 1500, transactions);
        Mockito.when(userRepository.findById(1)).thenReturn(Optional.ofNullable(user));
        User user1 = userService.getUser(1);
        assertEquals(1, user1.getId());
        assertEquals("sagar123", user1.getUserName());
    }

    @Test
    public void testCreditAmount(){
        Mockito.when(userService.creditAmount(1, 500)).thenReturn(user);
        User user1 = userService.creditAmount(1, 500);
        assertEquals("sagar123", user1.getUserName());
    }

}
