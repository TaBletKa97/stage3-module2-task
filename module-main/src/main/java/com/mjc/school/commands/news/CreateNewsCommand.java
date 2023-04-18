package com.mjc.school.commands.news;

import com.mjc.school.controller.implementation.NewsControllerImpl;
import com.mjc.school.service.dto.NewsDTORequest;
import com.mjc.school.service.dto.NewsDTOResponse;
import com.mjc.school.utils.Utils;

import java.util.Scanner;

import static com.mjc.school.utils.Constants.*;

public class CreateNewsCommand extends AbstractNewsCommand {

    public CreateNewsCommand(NewsControllerImpl controller) {
        super(controller);
    }

    @Override
    public void execute(Scanner sc) {
        System.out.println(TYPE_TITLE);
        String title = sc.nextLine();
        System.out.println(TYPE_ARTICLE);
        String content = sc.nextLine();
        long authorId = Utils.getLongFromScanner(sc, TYPE_AUTHOR_ID);
        NewsDTOResponse newsModel = controller.create(new NewsDTORequest(title,
                content, authorId));
        System.out.println(NEWS_CREATED);
        System.out.println(newsModel);
    }

    @Override
    public String getDescription() {
        return "Create news.";
    }
}
