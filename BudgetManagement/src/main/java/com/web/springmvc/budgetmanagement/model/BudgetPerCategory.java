package com.web.springmvc.budgetmanagement.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BudgetPerCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long amount;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private IconNote iconNote;

    @Override
    public String toString() {
        return "BudgetPerCategory{" +
                "id=" + id +
                ", amount=" + amount +
                '}';
    }
}
