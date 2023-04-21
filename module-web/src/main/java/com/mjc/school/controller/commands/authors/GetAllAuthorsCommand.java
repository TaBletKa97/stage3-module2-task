package com.mjc.school.controller.commands.authors;

import com.mjc.school.controller.BaseController;
import com.mjc.school.service.dto.AuthorDTORequest;
import com.mjc.school.service.dto.AuthorDTOResponse;

import java.util.Scanner;

public class GetAllAuthorsCommand extends AbstractAuthorCommand {
    public GetAllAuthorsCommand(BaseController<AuthorDTORequest, AuthorDTOResponse, Long> controller) {
        super(controller);
    }

    @Override
    public void execute(Scanner sc) {
        controller.readAll().forEach(System.out::println);
    }

    @Override
    public String getDescription() {
        return "Get all authors.";
    }
}
