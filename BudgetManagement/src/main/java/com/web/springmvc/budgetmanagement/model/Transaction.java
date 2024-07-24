package com.web.springmvc.budgetmanagement.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private long amount;
    private long receiveAmount;
    private String name;

    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private IconNote iconNote;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private Account transferAccount;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private Account receiveAccount;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private User user;

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", amount=" + amount +
                ", receiveAmount=" + receiveAmount +
                ", name='" + name + '\'' +
                ", transactionType=" + transactionType +
                '}';
    }
}
