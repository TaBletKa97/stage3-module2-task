package com.mjc.school.repository.implementation;

import com.mjc.school.repository.model.Author;
import com.mjc.school.repository.model.data.DataSource;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Repository
@DependsOn("dataSource")
public class AuthorRepositoryImpl extends AbstractRepository<Author> {

    public AuthorRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    @PostConstruct
    void init() {
        data = dataSource.getAuthors();
    }

    @Override
    public Author update(Author entity) {
        Author author = readById(entity.getId()).get();
        author.setName(entity.getName());
        author.setLastUpdateDate(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
        return author;
    }

}

