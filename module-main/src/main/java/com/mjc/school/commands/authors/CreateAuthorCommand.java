package com.mjc.school.commands.authors;

import com.mjc.school.controller.BaseController;
import com.mjc.school.service.dto.AuthorDTORequest;
import com.mjc.school.service.dto.AuthorDTOResponse;

import java.util.Scanner;

import static com.mjc.school.utils.Constants.AUTHOR_CREATED;
import static com.mjc.school.utils.Constants.TYPE_A_NAME;

public class CreateAuthorCommand extends AbstractAuthorCommand {

    public CreateAuthorCommand(BaseController<AuthorDTORequest, AuthorDTOResponse, Long> controller) {
        super(controller);
    }

    @Override
    public void execute(Scanner sc) {
        System.out.println(TYPE_A_NAME);
        String name = sc.nextLine();
        AuthorDTORequest request = new AuthorDTORequest();
        request.setName(name);
        AuthorDTOResponse authorDTOResponse = controller.create(request);
        System.out.println(AUTHOR_CREATED);
        System.out.println(authorDTOResponse);
    }

    @Override
    public String getDescription() {
        return "Create author.";
    }
}
