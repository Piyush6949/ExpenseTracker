package org.expensetracker.budget.repository;

import org.expensetracker.authentication.entity.User;
import org.expensetracker.budget.entity.Budget;
import org.expensetracker.category.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface BudgetRepository extends JpaRepository<Budget, UUID> {


    boolean existsByUserAndCategoryAndMonthAndYear(User user, Category category, Short month, Integer year);

    List<Budget> findAllByUser(User user);

    Optional<Budget> findByIdAndUser(UUID budgetId, User user);
}
