package com.mjc.school.service.validator;

import com.mjc.school.service.dto.NewsDTORequest;
import com.mjc.school.service.exceptions.ServiceErrorCode;
import com.mjc.school.service.exceptions.ValidatingException;
import org.springframework.stereotype.Service;

@Service
public class NewsRequestValidator {
    public void validate(NewsDTORequest req) throws ValidatingException {
        final String contentWord = "Content";
        final String titleWord = "Title";
        final int minLength = 5;
        final int maxContentLength = 255;
        final int maxTitleLength = 30;
        String title = req.getTitle();
        String content = req.getContent();
        if (title != null) {
            if (title.length() < minLength || title.length() > maxTitleLength) {
                throw new ValidatingException(String.format(
                        ServiceErrorCode.VALIDATE_STRING_LENGTH.getMessage(), titleWord,
                        minLength, maxTitleLength, titleWord, title));
            }
        }
        if (content != null) {
            if (content.length() < minLength || content.length() > maxContentLength) {
                throw new ValidatingException(String.format(
                        ServiceErrorCode.VALIDATE_STRING_LENGTH.getMessage(), contentWord,
                        minLength, maxContentLength, contentWord, content));
            }
        }
    }
}
