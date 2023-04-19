package com.mjc.school;

import com.mjc.school.commands.BaseCommand;
import com.mjc.school.commands.ShutdownCommand;
import com.mjc.school.commands.authors.*;
import com.mjc.school.commands.news.*;
import com.mjc.school.controller.BaseController;
import com.mjc.school.service.dto.AuthorDTORequest;
import com.mjc.school.service.dto.AuthorDTOResponse;
import com.mjc.school.service.dto.NewsDTORequest;
import com.mjc.school.service.dto.NewsDTOResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan("com.mjc.school")
public class Config {

    @Bean
    public Map<String, BaseCommand> commands(BaseController<NewsDTORequest, NewsDTOResponse, Long> newsController,
                                             BaseController<AuthorDTORequest, AuthorDTOResponse, Long> authorController) {
        Map<String, BaseCommand> commandMap = new HashMap<>();
        commandMap.put("1", new GetAllNewsCommand(newsController));
        commandMap.put("2", new GetNewsByIdCommand(newsController));
        commandMap.put("3", new CreateNewsCommand(newsController));
        commandMap.put("4", new UpdateNewsCommand(newsController));
        commandMap.put("5", new DeleteNewsCommand(newsController));
        commandMap.put("6", new GetAllAuthorsCommand(authorController));
        commandMap.put("7", new GetAuthorByIdCommand(authorController));
        commandMap.put("8", new CreateAuthorCommand(authorController));
        commandMap.put("9", new UpdateAuthorCommand(authorController));
        commandMap.put("10", new DeleteAuthorCommand(authorController));
        commandMap.put("0", new ShutdownCommand());
        return commandMap;
    }

    @Bean
    public Scanner scanner() {
        return new Scanner(System.in);
    }
}
