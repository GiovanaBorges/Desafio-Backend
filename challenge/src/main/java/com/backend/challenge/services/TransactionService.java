package com.backend.challenge.services;

import com.backend.challenge.DTO.TransactionDTO;
import com.backend.challenge.domain.transaction.Transaction;
import com.backend.challenge.domain.user.TYPEUSER;
import com.backend.challenge.domain.user.User;
import com.backend.challenge.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transaction;

    @Autowired
    private UserService userService;

    @Autowired
    RestTemplate restTemplate;

    public Transaction createTransaction(TransactionDTO transactiondto) throws Exception {
        User sender = userService.findUserById(transactiondto.senderId());
        User receiver = userService.findUserById(transactiondto.receiverId());

        userService.verifyTransaction(sender,transactiondto.amount());

        if(!verifyAuthorization()){
            throw new Exception("Transação não autorizada");
        }

        Transaction newTransaction = new Transaction();
        newTransaction.setSender(sender);
        newTransaction.setReceiver(receiver);
        newTransaction.setAmount(transactiondto.amount());
        newTransaction.setDate(LocalDateTime.now());

        // adding new wallet's values for receiver e sender wallet
        sender.setWallet(sender.getWallet().subtract(transactiondto.amount()));
        receiver.setWallet(receiver.getWallet().add(transactiondto.amount()));

        transaction.save(newTransaction);
        userService.saveUser(sender);
        userService.saveUser(receiver);

        return newTransaction;
    }

    public boolean verifyAuthorization(){
        ResponseEntity<Map> authorizationResp = restTemplate.getForEntity("https://run.mocky.io/v3/8fafdd68-a090-496f-8c9a-3442cf30dae6", Map.class);

        if(authorizationResp.getStatusCode() == HttpStatus.OK || authorizationResp.getBody().get("message") == "Autorizado") {
            return true;
        }else return false;
    }

    public List<Transaction> getAllTransactions(){
        return this.transaction.findAll();
    }
}
