package org.expensetracker.transaction.service;

import lombok.RequiredArgsConstructor;
import org.expensetracker.authentication.entity.User;
import org.expensetracker.transaction.dto.ExpenseDto;
import org.expensetracker.transaction.dto.TransactionRequestDto;
import org.expensetracker.transaction.entity.Transaction;
import org.expensetracker.transaction.repository.TransactionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static jakarta.persistence.GenerationType.UUID;

@Service
@RequiredArgsConstructor
public class ExpenseService {

    private final ModelMapper modelMapper;
    private final TransactionRepository transactionRepository;

    public List<ExpenseDto> getAllExpenses(){

        // get user from jwt
        User user = new User();

        List<Transaction> res = transactionRepository.findByUserAndType(user,Transaction.TransactionType.EXPENSE);

        return res.stream()
                .map(transaction -> modelMapper.map(transaction, ExpenseDto.class))
                .toList();
    }

    public ExpenseDto addExpense(TransactionRequestDto transactionRequestDto) {


        Transaction transaction = modelMapper.map(transactionRequestDto,Transaction.class);

        // get user from jwt
        User user = new User();
        transaction.setUser(user);

        return modelMapper.map(transactionRepository.save(transaction),ExpenseDto.class);
    }
}
