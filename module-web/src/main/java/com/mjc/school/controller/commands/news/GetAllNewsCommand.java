package com.mjc.school.controller.commands.news;

import com.mjc.school.controller.BaseController;
import com.mjc.school.service.dto.NewsDTORequest;
import com.mjc.school.service.dto.NewsDTOResponse;

import java.util.Scanner;

public class GetAllNewsCommand extends AbstractNewsCommand {

    public GetAllNewsCommand(BaseController<NewsDTORequest, NewsDTOResponse, Long> controller) {
        super(controller);
    }

    @Override
    public void execute(Scanner sc) {
        controller.readAll().forEach(System.out::println);
    }

    @Override
    public String getDescription() {
        return "Get all news";
    }
}
