package com.backend.challenge.services;

import com.backend.challenge.DTO.TransactionDTO;
import com.backend.challenge.domain.user.TYPEUSER;
import com.backend.challenge.domain.user.User;
import com.backend.challenge.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

@Service
public class UserService {
    @Autowired
    UserRepository repository;

    public User findUserById(Long id) throws Exception{
            return this.repository.findById(id).orElseThrow(() -> new Exception("usuario nao encontrado"));
    }

    public void saveUser(User user){
        this.repository.save(user);
    }

    public void verifyTransaction(User user, BigDecimal amount) throws Exception {
        if(user.getTypeUser() != TYPEUSER.COMMON){
            throw new Exception("Usuario lojista nao pode fazer transferencia");
        }

        if(user.getWallet().compareTo(amount) < 0){
            throw new Exception("Saldo insuficiente");
        }

    }

    public List<User> getAllUsers(){
        return this.repository.findAll();
    }
}
