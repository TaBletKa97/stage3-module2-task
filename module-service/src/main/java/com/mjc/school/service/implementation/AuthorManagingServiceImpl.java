package com.mjc.school.service.implementation;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.model.Author;
import com.mjc.school.service.BaseService;
import com.mjc.school.service.aop.anotations.OnDelete;
import com.mjc.school.service.dto.AuthorDTORequest;
import com.mjc.school.service.dto.AuthorDTOResponse;
import com.mjc.school.service.exceptions.SearchException;
import com.mjc.school.service.exceptions.ServiceErrorCode;
import com.mjc.school.service.mapper.AuthorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@DependsOn("authorRepositoryImpl")
public class AuthorManagingServiceImpl implements BaseService<AuthorDTORequest, AuthorDTOResponse, Long> {
    private final BaseRepository<Author, Long> authorDao;

    @Autowired
    public AuthorManagingServiceImpl(BaseRepository<Author, Long> authorDao) {
        this.authorDao = authorDao;
    }


    @Override
    public List<AuthorDTOResponse> readAll() {
        return authorDao.readAll().stream()
                .map(AuthorMapper.INSTANCE::mapAuthor)
                .collect(Collectors.toList());
    }

    @Override
    public AuthorDTOResponse readById(Long id) {
        return AuthorMapper.INSTANCE.mapAuthor(
                authorDao.readById(id).orElseThrow(
                        () -> new SearchException(String.format(
                                ServiceErrorCode.AUTHOR_ID_DOES_NOT_EXIST.getMessage(),
                                id))));
    }

    @Override
    public AuthorDTOResponse create(AuthorDTORequest createRequest) {
        Author author = AuthorMapper.INSTANCE.unmapAuthorReq(createRequest);
        LocalDateTime now = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
        author.setCreateDate(now);
        author.setLastUpdateDate(now);
        List<Author> authors = authorDao.readAll().stream()
                .sorted(Comparator.comparing(Author::getId))
                .collect(Collectors.toList());
        author.setId(authors.get(authors.size() - 1).getId() + 1);
        return AuthorMapper.INSTANCE.mapAuthor(authorDao.create(author));
    }

    @Override
    public AuthorDTOResponse update(AuthorDTORequest updateRequest) {
        AuthorDTOResponse old = readById(updateRequest.getId());
        Author newAuthor = AuthorMapper.INSTANCE.unmapAuthorReq(updateRequest);
        newAuthor.setCreateDate(old.getCreateDate());
        newAuthor.setLastUpdateDate(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
        return AuthorMapper.INSTANCE.mapAuthor(authorDao.create(newAuthor));
    }

    @OnDelete
    @Override
    public boolean deleteById(Long id) {
        return authorDao.deleteById(id);
    }
}
