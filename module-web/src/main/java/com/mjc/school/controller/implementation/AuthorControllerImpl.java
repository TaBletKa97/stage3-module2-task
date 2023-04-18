package com.mjc.school.controller.implementation;

import com.mjc.school.controller.BaseController;
import com.mjc.school.service.dto.AuthorDTORequest;
import com.mjc.school.service.dto.AuthorDTOResponse;
import com.mjc.school.service.implementation.AuthorManagingServiceImpl;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class AuthorControllerImpl implements BaseController<AuthorDTORequest, AuthorDTOResponse, Long> {
    private final AuthorManagingServiceImpl authorManagingService;

    public AuthorControllerImpl(AuthorManagingServiceImpl authorManagingService) {
        this.authorManagingService = authorManagingService;
    }

    @Override
    public List<AuthorDTOResponse> readAll() {
        return authorManagingService.readAll();
    }

    @Override
    public AuthorDTOResponse readById(Long id) {
        return authorManagingService.readById(id);
    }

    @Override
    public AuthorDTOResponse create(AuthorDTORequest createRequest) {
        return authorManagingService.create(createRequest);
    }

    @Override
    public AuthorDTOResponse update(AuthorDTORequest updateRequest) {
        return authorManagingService.update(updateRequest);
    }

    @Override
    public boolean deleteById(Long id) {
        return authorManagingService.deleteById(id);
    }
}
