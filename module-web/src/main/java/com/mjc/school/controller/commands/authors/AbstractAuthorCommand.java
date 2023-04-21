package com.mjc.school.controller.commands.authors;

import com.mjc.school.controller.commands.BaseCommand;
import com.mjc.school.controller.BaseController;
import com.mjc.school.service.dto.AuthorDTORequest;
import com.mjc.school.service.dto.AuthorDTOResponse;
import org.springframework.stereotype.Component;

@Component
public abstract class AbstractAuthorCommand implements BaseCommand {
    BaseController<AuthorDTORequest, AuthorDTOResponse, Long> controller;

    public AbstractAuthorCommand(BaseController<AuthorDTORequest, AuthorDTOResponse, Long> controller) {
        this.controller = controller;
    }
}
