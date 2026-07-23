package org.expensetracker.transaction.repository;

import org.expensetracker.authentication.entity.User;
import org.expensetracker.transaction.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, UUID> {

//    @Override
    List<Transaction> findByUserAndType(User user, Transaction.TransactionType transactionType);

    Optional<Transaction> findByIdAndUser(UUID id, User user);

    void deleteByIdAndUser(UUID id,User user);


}
