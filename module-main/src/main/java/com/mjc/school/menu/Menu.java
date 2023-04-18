package com.mjc.school.menu;

import com.mjc.school.commands.BaseCommand;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

import static com.mjc.school.utils.Constants.INCORRECT_VALUE;

@Component
public class Menu {
    private final Map<String, BaseCommand> commands;
    private final Scanner sc;

    public Menu(Map<String, BaseCommand> commands, Scanner sc) {
        this.commands = commands;
        this.sc = sc;
    }

    public void start() {
        final String option = "Choose an option:\n";
        String selectedVariant;
        String menu = commands.entrySet().stream()
                .sorted((o1, o2) -> {
                    if (o1.getKey().equals("0") || o2.getKey().equals("0")) {
                        return -1;
                    }
                    return o1.getKey().compareTo(o2.getKey());
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
