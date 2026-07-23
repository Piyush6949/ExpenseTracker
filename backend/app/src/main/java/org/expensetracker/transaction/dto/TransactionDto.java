package org.expensetracker.transaction.dto;

import lombok.*;
import org.expensetracker.transaction.entity.Transaction;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TransactionDto {

    private BigDecimal amount;

    private Transaction.TransactionType transactionType;

    private String description;

    private Transaction.PaymentMode paymentMode =  Transaction.PaymentMode.OTHER;

    private String receiptUrl;

    private String categoryName;

    private LocalDate date;
}
