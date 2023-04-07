package com.mjc.school.helper;

import com.mjc.school.controller.BaseController;
import com.mjc.school.service.dto.NewsDTORequest;
import com.mjc.school.service.dto.NewsDTOResponse;
import lombok.Cleanup;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;
import java.util.Scanner;

import static com.mjc.school.helper.Constants.*;

@Component
public class Helper {
    private final BaseController<NewsDTORequest, NewsDTOResponse, Long> controller;

    public Helper(BaseController<NewsDTORequest, NewsDTOResponse, Long> controller) {
        this.controller = controller;
    }

    public void start() {


        int selectedVariant;
        @Cleanup Scanner sc = new Scanner(System.in);
        do {
            System.out.println(MENU);
            try {
                selectedVariant = sc.nextInt();
            } catch (NoSuchElementException e) {
                sc.nextLine();
                selectedVariant = -1;
            }
            try {
                switch (selectedVariant) {
                    case 1 -> getAllNews();
                    case 2 -> getNewsById(sc);
                    case 3 -> createNews(sc);
                    case 4 -> updateNews(sc);
                    case 5 -> deleteNews(sc);
                    case 0 -> System.out.println("Goodbye!");
                    default -> System.err.println(INCORRECT_VALUE);
                }
            } catch (RuntimeException e) {
                System.err.println("ERROR01 " + e.getMessage());
            }
        } while (selectedVariant != 0);
    }

    private void getAllNews() {
        controller.readAll().forEach(System.out::println);
    }

    private void getNewsById(Scanner sc)  {
        long id = enterId(sc, TYPE_NEWS_ID);
        System.out.println(controller.readById(id));

    }

    private void createNews(Scanner sc) {
        sc.nextLine();
        String title = enterString(sc, TYPE_TITLE);
        String content = enterString(sc, TYPE_ARTICLE);
        long authorId = enterId(sc, TYPE_AUTHOR_ID);
        NewsDTOResponse newsModel = controller.create(new NewsDTORequest(title,
                content, authorId));
        System.out.println(NEWS_CREATED);
        System.out.println(newsModel);
    }

    private void updateNews(Scanner sc) {
        long newsId = enterId(sc, TYPE_NEWS_ID);
        sc.nextLine();
        String title = enterString(sc, TYPE_A_NEW_TITLE);
        String content = enterString(sc, TYPE_A_NEW_ARTICLE);
        long authorId = enterId(sc, TYPE_AUTHOR_ID);
        System.out.println(controller.update(new NewsDTORequest(newsId,
                title, content, authorId)));

    }

    private void deleteNews(Scanner sc) {
        long newsId = enterId(sc, TYPE_NEWS_ID);
        System.out.println(controller.deleteById(newsId));
    }

    private long enterId(Scanner sc, String message) {
        boolean isLong = false;
        long selectedVariant;
        do {
            try {
                System.out.println(message);
                selectedVariant = sc.nextLong();
                isLong = true;
            } catch (NoSuchElementException e) {
                System.err.println(INCORRECT_VALUE);
                sc.nextLine();
                selectedVariant = 0;
            }
        } while (!isLong);
        return selectedVariant;
    }

    private String enterString(Scanner sc, String message) {
        boolean isCorrect = false;
        String string = "";
        do {
            try {
                System.out.println(message);
                string = sc.nextLine();
                isCorrect = true;
            } catch (NoSuchElementException e) {
                System.err.println(INCORRECT_VALUE);
            }
        } while (!isCorrect);
        return string;
    }
}
