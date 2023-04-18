package com.mjc.school.service.validator;

import com.mjc.school.service.dto.AuthorDTORequest;
import com.mjc.school.service.exceptions.ServiceErrorCode;
import com.mjc.school.service.exceptions.ValidatingException;
import org.springframework.stereotype.Service;

@Service
public class AuthorRequestValidator {
    public void validate(AuthorDTORequest req) throws ValidatingException {
        if (req.getName() != null) {
            final int minLength = 3;
            final int maxLength = 15;
            final String name = "Name";
            if (req.getName().length() < minLength ||
                    req.getName().length() > maxLength) {
                throw new ValidatingException(String.format(
                        ServiceErrorCode.VALIDATE_STRING_LENGTH.getMessage(),
                        name, minLength, maxLength, name, req.getName()));
            }
        }
    }
}
