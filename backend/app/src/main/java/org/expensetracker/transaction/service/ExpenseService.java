package org.expensetracker.transaction.service;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.expensetracker.authentication.entity.User;
import org.expensetracker.authentication.util.JwtUtil;
import org.expensetracker.transaction.dto.ExpenseDto;
import org.expensetracker.transaction.dto.TransactionRequestDto;
import org.expensetracker.transaction.entity.Transaction;
import org.expensetracker.transaction.repository.TransactionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExpenseService {

    private final ModelMapper modelMapper;
    private final TransactionRepository transactionRepository;
    private final JwtUtil jwtUtil;

    public List<ExpenseDto> getAllExpenses(){

        // get user from SecurityContext holder
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();

        List<Transaction> res = transactionRepository.findByUserAndType(user,Transaction.TransactionType.EXPENSE);

        return res.stream()
                .map(transaction -> modelMapper.map(transaction, ExpenseDto.class))
                .toList();
    }

    public ExpenseDto addExpense(TransactionRequestDto transactionRequestDto) {


        Transaction transaction = modelMapper.map(transactionRequestDto,Transaction.class);

        // get user from securityContext holder

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();

        transaction.setUser(user);

        return modelMapper.map(transactionRepository.save(transaction),ExpenseDto.class);
    }
}
