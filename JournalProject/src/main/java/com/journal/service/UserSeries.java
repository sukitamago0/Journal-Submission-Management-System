package com.journal.service;

import com.github.pagehelper.PageInfo;
import com.journal.pojo.Article;
import com.journal.pojo.basicClass.User;
import com.journal.utils.PageRequest;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserSeries {
    PageInfo<Article> findByAuthor(String searchWord, PageRequest page);
    PageInfo<Article> findByCategory(String searchWord, PageRequest page);
    PageInfo<Article>findByKeyWord(String searchWord, PageRequest page);
    PageInfo<Article> findByTitle(String searchWord, PageRequest page);

    User findByID(String userID);
    int updateUser(User user);
    int insertUser(User user);
    User findByPhoneAndPassword(User user);
    User findByEmailAndPassword(User user);
    List<Article> findArticleByKey(String searchWord);
    PageInfo<Article> findAllArticlesOrderByUpdateTime(int pageNum, int pageSize);
}
