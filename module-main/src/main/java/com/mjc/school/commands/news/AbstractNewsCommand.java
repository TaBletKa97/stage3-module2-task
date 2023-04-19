package com.mjc.school.commands.news;

import com.mjc.school.commands.BaseCommand;
import com.mjc.school.controller.BaseController;
import com.mjc.school.service.dto.NewsDTORequest;
import com.mjc.school.service.dto.NewsDTOResponse;
import org.springframework.stereotype.Component;

@Component
public abstract class AbstractNewsCommand implements BaseCommand {
    BaseController<NewsDTORequest, NewsDTOResponse, Long> controller;

    public AbstractNewsCommand(BaseController<NewsDTORequest, NewsDTOResponse, Long> controller) {
        this.controller = controller;
    }
}
