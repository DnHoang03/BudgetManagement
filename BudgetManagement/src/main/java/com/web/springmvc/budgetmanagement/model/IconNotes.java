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
public class IconNotes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    private Icons icon;

    @OneToMany(mappedBy = "iconNotes", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Notes> notes = new ArrayList<>();

    @OneToMany(mappedBy = "iconNotes", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BudgetPerCategory> budgetPerCategories = new ArrayList<>();

    @OneToMany(mappedBy = "iconNotes", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AccountNotes> accountNotes = new ArrayList<>();
}
