package com.backend.challenge.DTO;

import com.backend.challenge.domain.user.TYPEUSER;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.math.BigDecimal;

public record UserDTO(
        String fullName,

        String cpf,

        String email,
        String password,
        TYPEUSER typeUser,
        BigDecimal wallet
) {
}
