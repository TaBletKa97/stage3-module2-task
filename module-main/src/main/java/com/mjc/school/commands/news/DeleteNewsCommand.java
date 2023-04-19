package com.mjc.school.commands.news;

import com.mjc.school.controller.BaseController;
import com.mjc.school.service.dto.NewsDTORequest;
import com.mjc.school.service.dto.NewsDTOResponse;
import com.mjc.school.utils.Utils;

import java.util.Scanner;

import static com.mjc.school.utils.Constants.TYPE_NEWS_ID;

public class DeleteNewsCommand extends AbstractNewsCommand {

    public DeleteNewsCommand(BaseController<NewsDTORequest, NewsDTOResponse, Long> controller) {
        super(controller);
    }

    @Override
    public void execute(Scanner sc) {
        long newsId = Utils.getLongFromScanner(sc, TYPE_NEWS_ID);
        System.out.println(controller.deleteById(newsId));
    }

    @Override
    public String getDescription() {
        return "Remove news by id.";
    }
}