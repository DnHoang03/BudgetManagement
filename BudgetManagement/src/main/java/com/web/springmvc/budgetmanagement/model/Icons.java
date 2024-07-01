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
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Icons {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "TEXT")
    private String url;
    @Enumerated(EnumType.STRING)
    private IconTypes type;

    @OneToMany(mappedBy = "icon", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Accounts> accounts = new ArrayList<>();

    @OneToMany(mappedBy = "icon", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<IconNotes> iconNotes = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    private IconCategories iconCategory;
}
