package com.mjc.school.service.implementation;

import com.mjc.school.repository.implementation.AuthorRepositoryImpl;
import com.mjc.school.repository.implementation.NewsRepositoryImpl;
import com.mjc.school.repository.model.Author;
import com.mjc.school.repository.model.News;
import com.mjc.school.service.BaseService;
import com.mjc.school.service.dto.NewsDTORequest;
import com.mjc.school.service.dto.NewsDTOResponse;
import com.mjc.school.service.exceptions.SearchException;
import com.mjc.school.service.exceptions.ServiceErrorCode;
import com.mjc.school.service.mapper.NewsMapper;
import com.mjc.school.service.validator.NewsRequestValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

import static com.mjc.school.service.utils.Constants.ERROR_MSG_AUTHOR_SEARCH;

@Service
public class NewsManagingServiceImpl implements BaseService<NewsDTORequest,
        NewsDTOResponse, Long> {
    private final ApplicationContext ctx;
    private final NewsRepositoryImpl newsDao;
    private final AuthorRepositoryImpl authorDao;
    private final NewsRequestValidator requestValidator;

    @Autowired
    public NewsManagingServiceImpl(ApplicationContext ctx, NewsRepositoryImpl newsDao,
                                   AuthorRepositoryImpl authorDao,
                                   NewsRequestValidator requestValidator) {
        this.ctx = ctx;
        this.newsDao = newsDao;
        this.authorDao = authorDao;
        this.requestValidator = requestValidator;
    }

    @Override
    public List<NewsDTOResponse> readAll() {
        return newsDao.readAll().stream()
                .map(NewsMapper.INSTANCE::mapNews)
                .collect(Collectors.toList());
    }

    @Override
    public NewsDTOResponse readById(Long id) {
        News news = newsDao.readById(id).orElseThrow(
                () -> new SearchException(String.format(
                        ServiceErrorCode.NEWS_ID_DOES_NOT_EXIST.getMessage(), id))
        );
        return NewsMapper.INSTANCE.mapNews(news);
    }

    @Override
    public NewsDTOResponse create(NewsDTORequest req) {
        requestValidator.validate(req);
        Author author = authorDao.readById(req.getAuthorId()).orElseThrow(
                () -> new SearchException(ERROR_MSG_AUTHOR_SEARCH)
        );
        News news = ctx.getBean(News.class);
        news.setTitle(req.getTitle());
        news.setContent(req.getContent());
        LocalDateTime now = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
        news.setCreateDate(now);
        news.setLastUpdateDate(now);
        news.setAuthor(author);
        news = newsDao.create(news);
        return NewsMapper.INSTANCE.mapNews(news);
    }

    @Override
    public NewsDTOResponse update(NewsDTORequest req)  {
        requestValidator.validate(req);
        News news = NewsMapper.INSTANCE.unmapNewsReq(req);
        readById(req.getId());
        Author author = authorDao.readById(req.getAuthorId()).orElseThrow(
                () -> new SearchException(ERROR_MSG_AUTHOR_SEARCH)
        );
        news.setAuthor(author);
        News update = newsDao.update(news);
        return NewsMapper.INSTANCE.mapNews(update);
    }

    @Override
    public boolean deleteById(Long id) {
        return newsDao.deleteById(id);
    }
}
