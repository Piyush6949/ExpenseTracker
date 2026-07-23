package org.expensetracker.transaction.service;


import lombok.RequiredArgsConstructor;
import org.expensetracker.authentication.entity.User;
import org.expensetracker.authentication.util.JwtUtil;
import org.expensetracker.common.util.UserUtil;
import org.expensetracker.transaction.dto.TransactionDto;
import org.expensetracker.transaction.dto.TransactionRequestDto;
import org.expensetracker.transaction.entity.Transaction;
import org.expensetracker.transaction.repository.TransactionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IncomeService {

    private final ModelMapper modelMapper;
    private final TransactionRepository transactionRepository;


    public List<TransactionDto> getAllIncomes(){

        // get user from SecurityContext holder
        User user = UserUtil.getCurrentUser();

        List<Transaction> res = transactionRepository.findByUserAndType(user,Transaction.TransactionType.INCOME);

        return res.stream()
                .map(transaction -> modelMapper.map(transaction, TransactionDto.class))
                .toList();
    }

    public TransactionDto addIncome(TransactionRequestDto transactionRequestDto) {


        Transaction transaction = modelMapper.map(transactionRequestDto,Transaction.class);

        // get user from securityContext holder

        User user = UserUtil.getCurrentUser();

        transaction.setUser(user);

        return modelMapper.map(transactionRepository.save(transaction), TransactionDto.class);
    }
}
