package org.expensetracker.budget.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.expensetracker.budget.dto.BudgetRequestDto;
import org.expensetracker.budget.dto.BudgetResponseDto;
import org.expensetracker.budget.service.BudgetService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/budgets")
@RequiredArgsConstructor
public class BudgetController {

    private final BudgetService budgetService;

    @PostMapping
    public ResponseEntity<BudgetResponseDto> createBudget(
            @Valid @RequestBody BudgetRequestDto requestDto
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(budgetService.createBudget(requestDto));
    }

    @GetMapping
    public ResponseEntity<List<BudgetResponseDto>> getAllBudgets() {
        return ResponseEntity.ok(budgetService.getAllBudgets());
    }

    @GetMapping("/{budgetId}")
    public ResponseEntity<BudgetResponseDto> getBudgetById(
            @PathVariable UUID budgetId
    ) {
        return ResponseEntity.ok(budgetService.getBudgetById(budgetId));
    }

    @PutMapping("/{budgetId}")
    public ResponseEntity<BudgetResponseDto> updateBudget(
            @PathVariable UUID budgetId,
            @Valid @RequestBody BudgetRequestDto requestDto
    ) {
        return ResponseEntity.ok(
                budgetService.updateBudget(budgetId, requestDto)
        );
    }

    @DeleteMapping("/{budgetId}")
    public ResponseEntity<Void> deleteBudget(
            @PathVariable UUID budgetId
    ) {
        budgetService.deleteBudget(budgetId);
        return ResponseEntity.noContent().build();
    }
}