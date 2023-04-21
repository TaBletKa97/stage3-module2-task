package com.mjc.school;

import com.mjc.school.controller.menu.Menu;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(Config.class);
        Menu menu = context.getBean(Menu.class);
        menu.start();
    }
}
