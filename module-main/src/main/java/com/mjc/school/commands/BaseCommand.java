package com.mjc.school.commands;

import java.util.Scanner;

public interface BaseCommand {
    void execute(Scanner sc);
    String getDescription();
}
