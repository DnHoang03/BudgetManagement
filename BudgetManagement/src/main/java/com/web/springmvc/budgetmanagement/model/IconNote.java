package com.web.springmvc.budgetmanagement.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
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

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @Enumerated(EnumType.STRING)
    private IconNoteType iconNoteType;

    @OneToMany(mappedBy = "iconNote", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Transaction> transactions = new ArrayList<>();

    @OneToMany(mappedBy = "iconNote", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<BudgetPerCategory> budgetPerCategories = new ArrayList<>();

    @Override
    public String toString() {
        return "IconNote{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", iconNoteType=" + iconNoteType +
                '}';
    }
}
