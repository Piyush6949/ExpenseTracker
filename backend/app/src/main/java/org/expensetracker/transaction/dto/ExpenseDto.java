package org.expensetracker.transaction.dto;

import lombok.*;
import org.expensetracker.authentication.entity.Category;
import org.expensetracker.transaction.entity.Transaction;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ExpenseDto {
    private UUID id;

    private Transaction.TransactionType type;

    private BigDecimal amount;

    private String description;

    private Transaction.PaymentMode paymentMode =  Transaction.PaymentMode.OTHER;

    private String receiptUrl;

    private Category category;

    private LocalDate date;
}
