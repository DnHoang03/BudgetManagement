package com.web.springmvc.budgetmanagement.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private long amount;
    @Column(columnDefinition = "TEXT")
    private String note;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private AccountType accountType;

    @ManyToOne(fetch = FetchType.LAZY)
    private Currency currency;

    @ManyToOne(fetch = FetchType.LAZY)
    private Icon icon;

    @OneToMany(mappedBy = "transferAccount", cascade = CascadeType.ALL)
    private List<Transaction> transferTransactions = new ArrayList<>();

    @OneToMany(mappedBy = "receiveAccount", cascade = CascadeType.ALL)
    private List<Transaction> receiveTransactions = new ArrayList<>();

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", amount=" + amount +
                ", note='" + note + '\'' +
                ", accountType=" + accountType +
                '}';
    }
}
