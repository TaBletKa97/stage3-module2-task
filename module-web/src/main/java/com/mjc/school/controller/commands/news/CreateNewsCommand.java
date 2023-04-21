package com.mjc.school.controller.commands.news;

import com.mjc.school.controller.BaseController;
import com.mjc.school.controller.utils.Constants;
import com.mjc.school.controller.utils.Utils;
import com.mjc.school.service.dto.NewsDTORequest;
import com.mjc.school.service.dto.NewsDTOResponse;

import java.util.Scanner;

public class CreateNewsCommand extends AbstractNewsCommand {

    public CreateNewsCommand(BaseController<NewsDTORequest, NewsDTOResponse, Long> controller) {
        super(controller);
    }

    @Override
    public void execute(Scanner sc) {
        System.out.println(Constants.TYPE_TITLE);
        String title = sc.nextLine();
        System.out.println(Constants.TYPE_ARTICLE);
        String content = sc.nextLine();
        long authorId = Utils.getLongFromScanner(sc, Constants.TYPE_AUTHOR_ID);
        NewsDTOResponse newsModel = controller.create(new NewsDTORequest(title,
                content, authorId));
        System.out.println(Constants.NEWS_CREATED);
        System.out.println(newsModel);
    }

    @Override
    public String getDescription() {
        return "Create news.";
    }
}
