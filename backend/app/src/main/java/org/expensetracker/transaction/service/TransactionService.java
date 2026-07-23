package org.expensetracker.transaction.service;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.expensetracker.authentication.entity.User;
import org.expensetracker.common.util.UserUtil;
import org.expensetracker.transaction.dto.TransactionDto;
import org.expensetracker.transaction.dto.TransactionRequestDto;
import org.expensetracker.transaction.entity.Transaction;
import org.expensetracker.transaction.repository.TransactionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final ModelMapper modelMapper;

    public TransactionDto updateTransaction(UUID id, @Valid TransactionRequestDto transactionRequestDto) {

        User user = UserUtil.getCurrentUser();

        Transaction transaction = transactionRepository.findByIdAndUser(id,user)
                .orElseThrow(() -> new RuntimeException("Transaction Not found"));


        modelMapper.map(transactionRequestDto, transaction);

        transaction.setId(id);
        transaction.setUser(user);

        Transaction updatedTransaction = transactionRepository.save(transaction);

        return modelMapper.map(updatedTransaction, TransactionDto.class);

    }

    public void deleteTransaction(UUID id) {
        User user = UserUtil.getCurrentUser();

        transactionRepository.deleteByIdAndUser(id,user);
    }

}
