package com.mjc.school.commands.news;

import com.mjc.school.controller.BaseController;
import com.mjc.school.service.dto.NewsDTORequest;
import com.mjc.school.service.dto.NewsDTOResponse;
import com.mjc.school.utils.Utils;

import java.util.Scanner;

import static com.mjc.school.utils.Constants.*;

public class UpdateNewsCommand extends AbstractNewsCommand {

    public UpdateNewsCommand(BaseController<NewsDTORequest, NewsDTOResponse, Long> controller) {
        super(controller);
    }

    @Override
    public void execute(Scanner sc) {
        long newsId = Utils.getLongFromScanner(sc, TYPE_NEWS_ID);
        System.out.println(TYPE_A_NEW_TITLE);
        String title = sc.nextLine();
        System.out.println(TYPE_A_NEW_ARTICLE);
        String content = sc.nextLine();
        long authorId = Utils.getLongFromScanner(sc, TYPE_AUTHOR_ID);
        System.out.println(controller.update(new NewsDTORequest(newsId,
                title, content, authorId)));
    }

    @Override
    public String getDescription() {
        return "Update news by id.";
    }
}
