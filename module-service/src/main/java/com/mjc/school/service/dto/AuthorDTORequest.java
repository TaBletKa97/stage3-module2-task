package com.mjc.school.service.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AuthorDTORequest {
    private Long id;
    private String name;
}
