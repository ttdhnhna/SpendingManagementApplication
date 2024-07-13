package com.project.SpendingManagementApplication;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.project.SpendingManagementApplication.entity.User;
import com.project.SpendingManagementApplication.repository.UserRepository;
import com.project.SpendingManagementApplication.service.UserService;

import jakarta.transaction.Transactional;

@SpringBootTest
public class UserServiceTest {
    @Mock 
    private UserRepository repository;

    @InjectMocks 
    private UserService service;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @Transactional
    void testRegisster(){
        BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
        User user = new User();
        user.setEmail("vidu@gmail.com");
        user.setHoten("dat");
        user.setPassword("123");

        when(repository.save(any(User.class))).thenReturn(user);
        service.saveRegistration(user);

        assertEquals("vidu@gmail.com", user.getEmail());
        assertEquals("dat", user.getHoten());
        assertEquals(true, passwordEncoder.matches("123", user.getPassword()));
    }
}
