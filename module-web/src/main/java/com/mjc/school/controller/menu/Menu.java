package com.mjc.school.controller.menu;

import com.mjc.school.controller.commands.BaseCommand;
import com.mjc.school.controller.commands.ShutdownCommand;
import com.mjc.school.controller.commands.authors.*;
import com.mjc.school.controller.commands.news.*;
import com.mjc.school.controller.implementation.AuthorControllerImpl;
import com.mjc.school.controller.implementation.NewsControllerImpl;
import org.springframework.stereotype.Component;
import org.springframework.util.comparator.Comparators;

import javax.annotation.PreDestroy;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

import static com.mjc.school.controller.utils.Constants.INCORRECT_VALUE;

@Component
public class Menu {
    private final Map<String, BaseCommand> commands;
    private final Scanner sc;

    public Menu(NewsControllerImpl newsController, AuthorControllerImpl authorController) {
        this.commands = new HashMap<>();
        commands.put("1", new GetAllNewsCommand(newsController));
        commands.put("2", new GetNewsByIdCommand(newsController));
        commands.put("3", new CreateNewsCommand(newsController));
        commands.put("4", new UpdateNewsCommand(newsController));
        commands.put("5", new DeleteNewsCommand(newsController));
        commands.put("6", new GetAllAuthorsCommand(authorController));
        commands.put("7", new GetAuthorByIdCommand(authorController));
        commands.put("8", new CreateAuthorCommand(authorController));
        commands.put("9", new UpdateAuthorCommand(authorController));
        commands.put("10", new DeleteAuthorCommand(authorController));
        commands.put("0", new ShutdownCommand());
        this.sc = new Scanner(System.in);
    }

    public void start() {
        final String option = "Choose an option:\n";
        String selectedVariant;
        String menu = commands.entrySet().stream()
                .sorted((o1, o2) -> {
                    if (o1.getKey().equals("0") || o2.getKey().equals("0")) {
                        return -1;
                    }
                    int int1 = Integer.parseInt(o1.getKey());
                    int int2 = Integer.parseInt(o2.getKey());
                    return int1 - int2;
                })
                .map(e -> e.getKey() + ". " + e.getValue().getDescription())
                .collect(Collectors.joining("\n"));

        while (true) {
            System.out.println(option + menu);
            try {
                selectedVariant = sc.nextLine();
                if (!commands.containsKey(selectedVariant)) {
                    System.out.println(INCORRECT_VALUE);
                    continue;
                }
                commands.get(selectedVariant).execute(sc);
            } catch (RuntimeException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    @PreDestroy
    private void close() {
        if (sc != null) {
            sc.close();
        }
    }
}
