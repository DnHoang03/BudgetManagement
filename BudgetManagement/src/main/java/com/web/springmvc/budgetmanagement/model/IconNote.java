package com.web.springmvc.budgetmanagement.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class IconNote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    private Icon icon;

    @Enumerated(EnumType.STRING)
    private IconNoteType iconNoteType;

    @OneToMany(mappedBy = "iconNote", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Transaction> transactions = new ArrayList<>();

    @OneToMany(mappedBy = "iconNote", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BudgetPerCategory> budgetPerCategories = new ArrayList<>();

}
