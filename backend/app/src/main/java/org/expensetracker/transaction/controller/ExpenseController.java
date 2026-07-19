package org.expensetracker.transaction.controller;

import lombok.RequiredArgsConstructor;
import org.expensetracker.transaction.dto.ExpenseDto;
import org.expensetracker.transaction.dto.TransactionRequestDto;
import org.expensetracker.transaction.entity.Transaction;
import org.expensetracker.transaction.service.ExpenseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/expense")
@RequiredArgsConstructor
public class ExpenseController {

    private final ExpenseService expenseService;

    @GetMapping
    public List<ExpenseDto> getAllExpenses(){
        return expenseService.getAllExpenses();
    }

    @PostMapping
    public ExpenseDto addExpense(@RequestBody TransactionRequestDto transactionRequestDto){
        // setting expense type
        transactionRequestDto.setType(Transaction.TransactionType.EXPENSE);
        return expenseService.addExpense(transactionRequestDto);
    }
}
