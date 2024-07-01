package com.web.springmvc.budgetmanagement.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Accounts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private long amount;
    @Column(columnDefinition = "TEXT")
    private String note;

    @ManyToOne(fetch = FetchType.LAZY)
    private Users user;

    @ManyToOne(fetch = FetchType.LAZY)
    private AccountTypes accountType;

    @ManyToOne(fetch = FetchType.LAZY)
    private Currencies currency;

    @ManyToOne(fetch = FetchType.LAZY)
    private Icons icon;

    @OneToMany(mappedBy = "transferAccount", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Transactions> transferTransactions = new ArrayList<>();

    @OneToMany(mappedBy = "receiveAccount", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Transactions> receiveTransactions = new ArrayList<>();
}
