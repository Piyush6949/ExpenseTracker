package org.expensetracker.budget.entity;

import jakarta.persistence.*;
import lombok.*;
import org.expensetracker.authentication.entity.User;
import org.expensetracker.category.entity.Category;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(
        name = "budget",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"user_id", "category_id", "month", "year"})
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Budget {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal amount;

    @Column(nullable = false)
    private Short month;

    @Column(nullable = false)
    private Integer year;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}