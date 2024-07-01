package com.web.springmvc.budgetmanagement.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class NotesDto {
    private Long id;
    private long amount;
    private LocalDateTime createdAt;
    private String note;
    private String imageUrl;
    private Long iconNotesId;
    private Long userId;
}
