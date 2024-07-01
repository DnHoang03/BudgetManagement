package com.web.springmvc.budgetmanagement.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.boot.model.TypeDefinition;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountNotes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private long amount;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @Column(columnDefinition = "TEXT")
    private String note;
    @Column(columnDefinition = "TEXT")
    private String imageUrl;
    @ManyToOne(fetch = FetchType.LAZY)
    private IconNotes iconNotes;
    @ManyToOne(fetch = FetchType.LAZY)
    private Accounts account;
}
