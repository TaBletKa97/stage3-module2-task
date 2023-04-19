package com.mjc.school.commands.authors;

import com.mjc.school.controller.BaseController;
import com.mjc.school.service.dto.AuthorDTORequest;
import com.mjc.school.service.dto.AuthorDTOResponse;
import com.mjc.school.utils.Utils;

import java.util.Scanner;

import static com.mjc.school.utils.Constants.*;

public class UpdateAuthorCommand extends AbstractAuthorCommand {

    public UpdateAuthorCommand(BaseController<AuthorDTORequest, AuthorDTOResponse, Long> controller) {
        super(controller);
    }

    @Override
    public void execute(Scanner sc) {
        long id = Utils.getLongFromScanner(sc, TYPE_AUTHOR_ID);
        System.out.println(TYPE_A_NEW_NAME);
        String newName = sc.nextLine();
        AuthorDTORequest request = new AuthorDTORequest();
        request.setId(id);
        request.setName(newName);
        AuthorDTOResponse update = controller.update(request);
        System.out.println(AUTHOR_UPDATED);
        System.out.println(update);
    }

    @Override
    public String getDescription() {
        return "Update author.";
    }
}
