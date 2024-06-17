package com.journal.service.impl;

import com.github.pagehelper.PageInfo;
import com.journal.config.SpringConfig;
import com.journal.dao.UserDao;
import com.journal.pojo.Article;
import com.journal.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringConfig.class})
@WebAppConfiguration
public class UserSeriesImplTest {
    @Autowired
    UserDao userDao;
    @Autowired
    UserService userSeries;

    @Test
    public void findAllArticlesOrderByUpdateTime() {
        PageInfo<Article> order = userSeries.findAllArticlesOrderByUpdateTime(1, 5);
        // 输出分页信息
        System.out.println("总记录数：" + order.getTotal());
        System.out.println("总页数：" + order.getPages());
        System.out.println("当前页码：" + order.getPageNum());
        System.out.println("每页大小：" + order.getPageSize());
        System.out.println("当前页记录数：" + order.getSize());
        // 输出当前页的文章信息
        for (Article article : order.getList()) {
            System.out.println(article);
        }
    }

    @Test
    public void findArticleByKey() {
        List<Article> list = userSeries.findArticleByKey("小姐||科幻");
        for (Article article : list) {
            System.out.println(article);
        }
    }
}