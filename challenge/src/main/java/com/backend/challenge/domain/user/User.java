package com.backend.challenge.domain.user;

import com.backend.challenge.DTO.UserDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity(name="users")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fullName;
    @Column(unique=true)
    private String cpf;
    @Column(unique=true)
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private TYPEUSER typeUser;

    private BigDecimal wallet;

    public User(UserDTO user){
        this.setCpf(user.cpf());
        this.setTypeUser(user.typeUser());
        this.setEmail(user.email());
        this.setPassword(user.password());
        this.setFullName(user.fullName());
        this.setWallet(user.wallet());
    }
}
