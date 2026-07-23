package org.expensetracker.transaction.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.expensetracker.authentication.entity.Category;
import org.expensetracker.authentication.entity.User;
import org.expensetracker.transaction.entity.Transaction;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TransactionRequestDto {

    private Transaction.TransactionType type;

    private BigDecimal amount;

    private String description;

    private Transaction.PaymentMode paymentMode =  Transaction.PaymentMode.OTHER;

    private String receiptUrl;

    private Category category;

    private LocalDate date;

}
