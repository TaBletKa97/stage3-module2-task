package com.mjc.school.commands.authors;

import com.mjc.school.commands.BaseCommand;
import com.mjc.school.controller.implementation.AuthorControllerImpl;
import org.springframework.stereotype.Component;

@Component
public abstract class AbstractAuthorCommand implements BaseCommand {
    AuthorControllerImpl controller;

    public AbstractAuthorCommand(AuthorControllerImpl controller) {
        this.controller = controller;
    }
}
