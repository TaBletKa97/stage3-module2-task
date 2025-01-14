package com.mjc.school.repository.implementation;

import com.mjc.school.repository.model.News;
import com.mjc.school.repository.model.data.DataSource;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Repository
@DependsOn("dataSource")
public class NewsRepositoryImpl extends AbstractRepository<News> {

    public NewsRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    @PostConstruct
    void init() {
        data = dataSource.getNews();
    }

    @Override
    public News update(News entity) {
        News news = readById(entity.getId()).get();
        news.setTitle(entity.getTitle());
        news.setContent(entity.getContent());
        news.setAuthor(entity.getAuthor());
        news.setLastUpdateDate(LocalDateTime.now()
                .truncatedTo(ChronoUnit.SECONDS));
        return news;
    }
}
