package org.expensetracker.transaction.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.expensetracker.transaction.dto.TransactionDto;
import org.expensetracker.transaction.dto.TransactionRequestDto;
import org.expensetracker.transaction.entity.Transaction;
import org.expensetracker.transaction.service.ExpenseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/expense")
@RequiredArgsConstructor
public class ExpenseController {

    private final ExpenseService expenseService;

    @GetMapping
    public List<TransactionDto> getAllExpenses(){
        return expenseService.getAllExpenses();
    }

    // Add expense
    @PostMapping
    public TransactionDto addExpense(@RequestBody TransactionRequestDto transactionRequestDto){
        // setting expense type
        transactionRequestDto.setType(Transaction.TransactionType.EXPENSE);
        return expenseService.addExpense(transactionRequestDto);
    }

}
