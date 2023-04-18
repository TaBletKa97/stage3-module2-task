package com.mjc.school.service.aop;

import com.mjc.school.repository.implementation.NewsRepositoryImpl;
import com.mjc.school.repository.model.News;
import com.mjc.school.service.exceptions.ServiceErrorCode;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

@Component
@Aspect
public class DeleteAspect {
    private final Scanner sc;
    private final NewsRepositoryImpl newsDao;

    public DeleteAspect(Scanner sc, NewsRepositoryImpl newsDao) {
        this.sc = sc;
        this.newsDao = newsDao;
    }

    @Before("execution(* *())")
    public void test() {
        System.out.println("test");
    }

//    @Around("@annotation(com.mjc.school.service.aop.anotations.OnDelete) &&" +
//            " args(id)")
//    public Object cascadeDeletingAdvice(ProceedingJoinPoint jp, Long id) throws Throwable {
//        System.out.println("Choose an deleting option:");
//        System.out.println("1. Set authorId field for corresponding news to null");
//        System.out.println("2. Remove corresponding news.");
//
//        boolean isSetNull;
//        String variant = sc.nextLine();
//        switch (variant) {
//            case "1" -> isSetNull = true;
//            case "2" -> isSetNull = false;
//            default -> throw new RuntimeException(String.format(
//                    ServiceErrorCode.VALIDATE_INT_VALUE.getMessage(), variant
//            ));
//        }
//
//        Boolean proceed = (Boolean) jp.proceed();
//
//        if (proceed) {
//            List<News> relatedNews = newsDao.readAll().stream()
//                    .filter(news -> news.getAuthor().getId().equals(id))
//                    .collect(Collectors.toList());
//            if (isSetNull) {
//                relatedNews.forEach(news -> news.setAuthor(null));
//            } else {
//                relatedNews.forEach(news -> newsDao.deleteById(news.getId()));
//            }
//        }
//        return proceed;
//    }
}
