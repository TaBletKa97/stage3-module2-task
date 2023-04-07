package com.mjc.school.repository.utils;

import com.mjc.school.repository.model.Author;
import com.mjc.school.repository.model.News;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class DataGenerator {
    private static final int numLines = 30;
    private static final String authorFileName = "authors";
    private static final String newsFileName = "news";
    private static final String contentFileName = "content";

    private final ApplicationContext ctx;
    @Getter
    private final List<Author> authors = new ArrayList<>();
    private final Random random;

    @Autowired
    public DataGenerator(ApplicationContext ctx) {
        this.ctx = ctx;
        this.random = new Random();
    }

    @PostConstruct
    private void init() {
        for (int i = 1; i <= 20; i++) {
            String name = getRandomStringFromFile(authorFileName);
            if (authors.stream().anyMatch(a -> a.getName().equals(name))) {
                i--;
                continue;
            }
            Author author = ctx.getBean(Author.class);
            author.setId((long) i);
            author.setName(name);
            authors.add(author);
            author.setCreateDate(getRandomDate().minusDays(45));
        }
    }

    public List<News> generateNews() {
        List<News> newsList = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            LocalDateTime time = getRandomDate();
            Author author = authors.get(random.nextInt(authors.size()));
            author.setLastUpdateDate(time);
            News news = ctx.getBean(News.class);
            news.setId((long) i);
            news.setTitle(getRandomStringFromFile(newsFileName));
            news.setContent(getRandomStringFromFile(contentFileName));
            news.setCreateDate(time);
            news.setLastUpdateDate(time);
            news.setAuthor(author);
            newsList.add(news);
        }
        return newsList;
    }

    private String getRandomStringFromFile(String fileName) {
        String resultLine;
        try {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            InputStream inputStream = classLoader.getResourceAsStream(fileName);
            InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            BufferedReader reader = new BufferedReader(streamReader);
            int desiredLine = random.nextInt(numLines);
            int lineCtr = 0;
            while ((resultLine = reader.readLine()) != null) {
                if (lineCtr == desiredLine) {
                    break;
                }
                lineCtr++;
            }
            return resultLine;
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    private LocalDateTime getRandomDate() {
        Random random = new Random();
        int endDay = 30;
        LocalDate day = LocalDate.now().plusDays(random.nextInt(endDay));
        int hour = random.nextInt(24);
        int minute = random.nextInt(60);
        int second = random.nextInt(60);
        LocalTime time = LocalTime.of(hour, minute, second);
        return LocalDateTime.of(day, time);
    }
}
