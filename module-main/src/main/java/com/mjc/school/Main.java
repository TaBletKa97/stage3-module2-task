package com.mjc.school;

import com.mjc.school.helper.Config;
import com.mjc.school.helper.Helper;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(Config.class);
        Helper helper = context.getBean(Helper.class);
        Logger log = context.getBean(Logger.class);
        log.info("In main:");
        helper.start();
    }
}
