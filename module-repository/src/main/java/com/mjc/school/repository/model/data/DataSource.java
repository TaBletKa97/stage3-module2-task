package com.mjc.school.repository.model.data;

import com.mjc.school.repository.model.Author;
import com.mjc.school.repository.model.News;
import com.mjc.school.repository.utils.DataGenerator;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Getter
@Component
public class DataSource {
    private final List<News> news = new ArrayList<>();
    private final List<Author> authors = new ArrayList<>();
    private final DataGenerator generator;

    @Autowired
    public DataSource(DataGenerator generator) {
        this.generator = generator;
    }

    @PostConstruct
    private void init() {
        authors.addAll(generator.getAuthors());
        List<News> generatedNews = generator.generateNews();
        news.addAll(generatedNews);
    }
}
