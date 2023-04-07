package com.mjc.school.repository.implementation;

import com.mjc.school.repository.model.Author;
import com.mjc.school.repository.model.data.DataSource;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.stream.Collectors;

@Repository
@DependsOn("dataSource")
public class AuthorRepositoryImpl extends AbstractRepository<Author> {
    private final Logger logger;

    public AuthorRepositoryImpl(Logger logger, DataSource dataSource) {
        this.logger = logger;
        this.dataSource = dataSource;
    }

    @Override
    @PostConstruct
    void init() {
        data = dataSource.getAuthors();
        logger.debug("In init:" + data.stream()
                .map(Author::toString)
                .collect(Collectors.joining("/")));
    }

    @Override
    public Author update(Author entity) {
        Author author = readById(entity.getId()).get();
        author.setName(entity.getName());
        author.setLastUpdateDate(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
        return author;
    }

}

