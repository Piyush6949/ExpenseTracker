package org.expensetracker.transaction.controller;

import lombok.RequiredArgsConstructor;
import org.expensetracker.transaction.dto.TransactionDto;
import org.expensetracker.transaction.dto.TransactionRequestDto;
import org.expensetracker.transaction.entity.Transaction;
import org.expensetracker.transaction.service.IncomeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/income")
@RequiredArgsConstructor
public class IncomeController {

    private final IncomeService incomeService;

    @GetMapping
    public List<TransactionDto> getAllIncome(){
        return incomeService.getAllIncomes();
    }

    // Add expense
    @PostMapping
    public TransactionDto addIncome(@RequestBody TransactionRequestDto transactionRequestDto){
        // setting expense type
        transactionRequestDto.setType(Transaction.TransactionType.INCOME);
        return incomeService.addIncome(transactionRequestDto);
    }

}
