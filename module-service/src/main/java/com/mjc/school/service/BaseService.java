package com.mjc.school.service;

import com.mjc.school.service.exceptions.SearchAuthorException;
import com.mjc.school.service.exceptions.SearchNewsException;
import com.mjc.school.service.exceptions.validation.ValidatingDTOException;

import java.util.List;

public interface BaseService<T, R, K> {
    List<R> readAll();

    R readById(K id);

    R create(T createRequest);

    R update(T updateRequest);

    boolean deleteById(K id);
}
