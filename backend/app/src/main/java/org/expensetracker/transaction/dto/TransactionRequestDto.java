package org.expensetracker.transaction.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.expensetracker.category.entity.Category;
import org.expensetracker.transaction.entity.Transaction;

import java.math.BigDecimal;
import java.time.LocalDate;

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
