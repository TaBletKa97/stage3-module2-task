package com.mjc.school.commands.news;

import com.mjc.school.controller.implementation.NewsControllerImpl;

import java.util.Scanner;

public class GetAllNewsCommand extends AbstractNewsCommand {

    public GetAllNewsCommand(NewsControllerImpl controller) {
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
