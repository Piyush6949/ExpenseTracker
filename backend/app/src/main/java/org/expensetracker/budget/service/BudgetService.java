package org.expensetracker.budget.service;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.expensetracker.authentication.entity.User;
import org.expensetracker.budget.repository.BudgetRepository;
import org.expensetracker.budget.dto.BudgetRequestDto;
import org.expensetracker.budget.dto.BudgetResponseDto;
import org.expensetracker.budget.entity.Budget;
import org.expensetracker.common.util.UserUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BudgetService {

    private final BudgetRepository budgetRepository;
    private final ModelMapper modelMapper;

    public BudgetResponseDto createBudget(@Valid BudgetRequestDto requestDto) {

        User user = UserUtil.getCurrentUser();

        if (budgetRepository.existsByUserAndCategoryAndMonthAndYear(
                user,
                requestDto.getCategory(),
                requestDto.getMonth(),
                requestDto.getYear()
        )) {
            throw new RuntimeException("Budget already exists for this category and month.");
        }

        Budget budget = modelMapper.map(requestDto, Budget.class);
        budget.setUser(user);

        Budget savedBudget = budgetRepository.save(budget);

        return modelMapper.map(savedBudget, BudgetResponseDto.class);
    }

    public List<BudgetResponseDto> getAllBudgets() {

        User user = UserUtil.getCurrentUser();

        return budgetRepository.findAllByUser(user)
                .stream()
                .map(budget -> modelMapper.map(budget, BudgetResponseDto.class))
                .toList();
    }

    public BudgetResponseDto getBudgetById(UUID budgetId) {

        User user = UserUtil.getCurrentUser();

        Budget budget = budgetRepository.findByIdAndUser(budgetId, user)
                .orElseThrow(() -> new RuntimeException("Budget not found."));

        return modelMapper.map(budget, BudgetResponseDto.class);
    }

    public BudgetResponseDto updateBudget(UUID budgetId, @Valid BudgetRequestDto requestDto) {

        User user = UserUtil.getCurrentUser();

        Budget budget = budgetRepository.findByIdAndUser(budgetId, user)
                .orElseThrow(() -> new RuntimeException("Budget not found."));

        budget.setCategory(requestDto.getCategory());
        budget.setAmount(requestDto.getAmount());
        budget.setMonth(requestDto.getMonth());
        budget.setYear(requestDto.getYear());

        return modelMapper.map(budgetRepository.save(budget), BudgetResponseDto.class);
    }

    public void deleteBudget(UUID budgetId) {

        User user = UserUtil.getCurrentUser();

        Budget budget = budgetRepository.findByIdAndUser(budgetId, user)
                .orElseThrow(() -> new RuntimeException("Budget not found."));

        budgetRepository.delete(budget);
    }
}
