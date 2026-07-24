package org.expensetracker.budget.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.expensetracker.category.entity.Category;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BudgetResponseDto {

    private Category category;

    private BigDecimal amount;

    private Short month;

    private Integer year;
}
