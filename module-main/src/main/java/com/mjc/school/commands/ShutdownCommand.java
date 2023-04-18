package com.mjc.school.commands;

import java.util.Scanner;

public class ShutdownCommand implements BaseCommand {
    @Override
    public void execute(Scanner sc) {
        System.exit(0);
    }

    @Override
    public String getDescription() {
        return "Exit";
    }
}
