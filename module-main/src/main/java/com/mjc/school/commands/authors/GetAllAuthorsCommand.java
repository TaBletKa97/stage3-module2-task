package com.mjc.school.commands.authors;

import com.mjc.school.controller.implementation.AuthorControllerImpl;

import java.util.Scanner;

public class GetAllAuthorsCommand extends AbstractAuthorCommand {

    public GetAllAuthorsCommand(AuthorControllerImpl controller) {
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
