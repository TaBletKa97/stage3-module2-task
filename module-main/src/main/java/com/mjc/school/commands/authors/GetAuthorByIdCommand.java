package com.mjc.school.commands.authors;

import com.mjc.school.controller.BaseController;
import com.mjc.school.service.dto.AuthorDTORequest;
import com.mjc.school.service.dto.AuthorDTOResponse;
import com.mjc.school.utils.Utils;

import java.util.Scanner;

import static com.mjc.school.utils.Constants.TYPE_AUTHOR_ID;

public class GetAuthorByIdCommand extends AbstractAuthorCommand {

    public GetAuthorByIdCommand(BaseController<AuthorDTORequest, AuthorDTOResponse, Long> controller) {
        super(controller);
    }

    @Override
    public void execute(Scanner sc) {
        long id = Utils.getLongFromScanner(sc, TYPE_AUTHOR_ID);
        System.out.println(controller.readById(id));
    }

    @Override
    public String getDescription() {
        return "Get author by id.";
    }
}
