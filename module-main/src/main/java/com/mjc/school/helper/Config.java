package com.mjc.school.helper;

import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.mjc.school")
public class Config {

    @Bean
    public Logger logger() {
        Logger log = Logger.getLogger("myLog");
        log.setLevel(Level.ALL);
        FileAppender appender = new FileAppender();
        appender.setFile("module-main/src/main/resources/logs.log");
        appender.setLayout(new PatternLayout("%d [%p, %C] %m;%n"));
        appender.activateOptions();
        Logger.getRootLogger().addAppender(appender);
        return log;
    }
}
