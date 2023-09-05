package com.backend.challenge.controllers;

import com.backend.challenge.DTO.UserDTO;
import com.backend.challenge.domain.user.User;
import com.backend.challenge.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService service;

    @PostMapping
    public ResponseEntity<User> saveUser(@RequestBody UserDTO user){
        User newuser = new User();
        newuser.setWallet(user.wallet());
        newuser.setTypeUser(user.typeUser());
        newuser.setCpf(user.cpf());
        newuser.setEmail(user.email());
        newuser.setPassword(user.password());
        newuser.setFullName(user.fullName());

        service.saveUser(newuser);

        return new ResponseEntity<>(newuser,HttpStatus.CREATED);
    }

    @GetMapping
    public List<User> getAllUsers(){
        return this.service.getAllUsers();
    }
}
