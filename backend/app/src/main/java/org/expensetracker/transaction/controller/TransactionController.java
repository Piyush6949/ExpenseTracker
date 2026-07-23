package org.expensetracker.transaction.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.expensetracker.transaction.dto.TransactionDto;
import org.expensetracker.transaction.dto.TransactionRequestDto;
import org.expensetracker.transaction.service.TransactionService;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/transaction")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @PutMapping("/{id}")
    public TransactionDto updateTransaction(
            @PathVariable UUID id,
            @RequestBody @Valid TransactionRequestDto transactionRequestDto) {

        return transactionService.updateTransaction(id, transactionRequestDto);
    }

    @DeleteMapping("/{id}")
    public void deleteTransaction(@PathVariable UUID id) {
        transactionService.deleteTransaction(id);
    }
}
