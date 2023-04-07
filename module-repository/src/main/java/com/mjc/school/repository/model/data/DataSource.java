package com.mjc.school.repository.model.data;

import com.mjc.school.repository.model.Author;
import com.mjc.school.repository.model.News;
import com.mjc.school.repository.utils.DataGenerator;
import lombok.Getter;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Component
@DependsOn("dataGenerator")
public class DataSource {
    @Autowired
    private final ApplicationContext ctx;

    private final List<News> news = new ArrayList<>();
    private final List<Author> authors = new ArrayList<>();
    private final Logger logger;

    @Autowired
    public DataSource(ApplicationContext ctx, Logger logger) {
        this.ctx = ctx;
        this.logger = logger;
    }

    @PostConstruct
    private void init() {
        DataGenerator generator = ctx.getBean(DataGenerator.class);
        authors.addAll(generator.getAuthors());
        List<News> news = generator.generateNews();
        this.news.addAll(news);
        logger.debug("In init:" + news.stream()
        .map(News::toString)
        .collect(Collectors.joining("/")));
    }
}
