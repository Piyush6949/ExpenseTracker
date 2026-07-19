package org.expensetracker.transaction.repository;

import org.expensetracker.authentication.entity.User;
import org.expensetracker.transaction.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, UUID> {

//    @Override
    List<Transaction> findByType(Transaction.TransactionType transactionType);
    List<Transaction> findByUserAndType(User user, Transaction.TransactionType transactionType);

//    public List<Transaction> find
}
