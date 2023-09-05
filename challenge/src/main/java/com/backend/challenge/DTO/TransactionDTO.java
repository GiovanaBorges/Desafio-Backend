package com.backend.challenge.DTO;

import java.math.BigDecimal;

public record TransactionDTO(
        Long senderId,
        Long receiverId,
        BigDecimal amount
) {
}
