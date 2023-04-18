package com.mjc.school.commands.news;

import com.mjc.school.commands.BaseCommand;
import com.mjc.school.controller.implementation.NewsControllerImpl;
import org.springframework.stereotype.Component;

@Component
public abstract class AbstractNewsCommand implements BaseCommand {
    NewsControllerImpl controller;

    public AbstractNewsCommand(NewsControllerImpl controller) {
        this.controller = controller;
    }
}
