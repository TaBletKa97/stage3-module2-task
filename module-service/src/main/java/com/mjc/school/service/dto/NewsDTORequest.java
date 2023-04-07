package com.mjc.school.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewsDTORequest {
    private Long id;
    private String title;
    private String content;
    private long authorId;

    public NewsDTORequest(Long id) {
        this.id = id;
    }

    public NewsDTORequest(String title, String content, Long authorId) {
        this.title = title;
        this.content = content;
        this.authorId = authorId;
    }
}
