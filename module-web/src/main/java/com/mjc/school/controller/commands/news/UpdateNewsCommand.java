package com.mjc.school.controller.commands.news;

import com.mjc.school.controller.BaseController;
import com.mjc.school.controller.utils.Constants;
import com.mjc.school.controller.utils.Utils;
import com.mjc.school.service.dto.NewsDTORequest;
import com.mjc.school.service.dto.NewsDTOResponse;

import java.util.Scanner;

public class UpdateNewsCommand extends AbstractNewsCommand {

    public UpdateNewsCommand(BaseController<NewsDTORequest, NewsDTOResponse, Long> controller) {
        super(controller);
    }

    @Override
    public void execute(Scanner sc) {
        long newsId = Utils.getLongFromScanner(sc, Constants.TYPE_NEWS_ID);
        System.out.println(Constants.TYPE_A_NEW_TITLE);
        String title = sc.nextLine();
        System.out.println(Constants.TYPE_A_NEW_ARTICLE);
        String content = sc.nextLine();
        long authorId = Utils.getLongFromScanner(sc, Constants.TYPE_AUTHOR_ID);
        System.out.println(controller.update(new NewsDTORequest(newsId,
                title, content, authorId)));
    }

    @Override
    public String getDescription() {
        return "Update news by id.";
    }
}
