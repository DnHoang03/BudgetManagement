package com.web.springmvc.budgetmanagement.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Icon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "TEXT")
    private String url;
    @Enumerated(EnumType.STRING)
    private IconType type;

    @OneToMany(mappedBy = "icon", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Account> accounts = new ArrayList<>();

    @OneToMany(mappedBy = "icon", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<IconNote> iconNotes = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private IconCategory iconCategory;

    @Override
    public String toString() {
        return "Icon{" +
                "id=" + id +
                ", url='" + url + '\'' +
                '}';
    }
}
