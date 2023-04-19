package com.mjc.school.commands.news;

import com.mjc.school.controller.BaseController;
import com.mjc.school.service.dto.NewsDTORequest;
import com.mjc.school.service.dto.NewsDTOResponse;
import com.mjc.school.utils.Utils;

import java.util.Scanner;

import static com.mjc.school.utils.Constants.TYPE_NEWS_ID;

public class GetNewsByIdCommand extends AbstractNewsCommand {

    public GetNewsByIdCommand(BaseController<NewsDTORequest, NewsDTOResponse, Long> controller) {
        super(controller);
    }

    @Override
    public void execute(Scanner sc) {
        long id = Utils.getLongFromScanner(sc, TYPE_NEWS_ID);
        System.out.println(controller.readById(id));
    }

    @Override
    public String getDescription() {
        return "Get news by id.";
    }
}
