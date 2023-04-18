package com.mjc.school.commands.authors;

import com.mjc.school.controller.implementation.AuthorControllerImpl;
import com.mjc.school.utils.Utils;

import java.util.Scanner;

public class DeleteAuthorCommand extends AbstractAuthorCommand {
    public DeleteAuthorCommand(AuthorControllerImpl controller) {
        super(controller);
    }

    @Override
    public void execute(Scanner sc) {
        final String typeAuthorId = "Type author id:";
        long longFromScanner = Utils.getLongFromScanner(sc, typeAuthorId);
        controller.deleteById(longFromScanner);
    }

    @Override
    public String getDescription() {
        return "Delete author by id.";
    }
}
