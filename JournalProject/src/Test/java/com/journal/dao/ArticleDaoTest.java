package com.journal.dao;

import com.journal.config.SpringConfig;
import com.journal.pojo.Article;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringConfig.class})
@WebAppConfiguration
public class ArticleDaoTest {
    @Autowired
ArticleDao dao;
    @Test
    public void findByAuthor() {
        List<Article> list = dao.findByAuthor("noob1");
        for (Article a:
             list) {
            System.out.println(a);
        }
    }

    @Test
    public void findByTitle() {
        List<Article> list = dao.findByTitle("女士");
        for (Article a:
                list) {
            System.out.println(a);
        }
    }

    @Test
    public void findByKeyWord() {
        List<Article> list = dao.findByKeyWord("Logistics");
        for (Article a:
                list) {
            System.out.println(a);
        }
    }

    @Test
    public void findByCategory() {
        List<Article> list = dao.findByCategory("科幻");
        for (Article a:
                list) {
            System.out.println(a);
        }
    }
}