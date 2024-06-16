package com.journal.dao;

import com.journal.pojo.Article;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
@Mapper
public interface ArticleDao {
    /**
     * 根据搜索词查询书籍以||分割（科幻||玄幻）
     * @param
     * @return
     */
    @Select({
            "SELECT * FROM article",
            "WHERE (userID IN (SELECT userID FROM user WHERE nickName LIKE CONCAT('%', #{searchWord}, '%'))",
            "OR title LIKE CONCAT('%', #{searchWord}, '%')",
            "OR keywords LIKE CONCAT('%', #{searchWord}, '%')",
            "OR categoryID IN (SELECT categoryID FROM category WHERE categoryName LIKE CONCAT('%', #{searchWord}, '%')))",
            "AND state = 3"
    })
    List<Article> findArticles(@Param("searchWord") String searchWord);

    /**
     * 根据作者搜索
     * @param searchWord
     * @return
     */
    @Select({
            "SELECT * FROM article",
            "WHERE (userID IN (SELECT userID FROM user WHERE nickName LIKE CONCAT('%', #{searchWord}, '%')))",
            "AND state = 3"
    })
    List<Article> findByAuthor(@Param("searchWord") String searchWord);

    /**
     * 根据类别搜索
     * @param searchWord
     * @return
     */
    @Select({
            "SELECT * FROM article",
            "WHERE categoryID IN (SELECT categoryID FROM category WHERE categoryName LIKE CONCAT('%', #{searchWord}, '%'))",
            "AND state = 3"
    })
    List<Article> findByCategory(@Param("searchWord") String searchWord);

    /**
     * 根据关键词搜索
     * @param searchWord
     * @return
     */
    @Select({
            "SELECT * FROM article",
            "WHERE keywords LIKE CONCAT('%', #{searchWord}, '%')",
            "AND state = 3"
    })
    List<Article> findByKeyWord(@Param("searchWord") String searchWord);

    /**
     * 根据题目搜索
     * @param searchWord
     * @return
     */
    @Select({
            "SELECT * FROM article",
            "WHERE title LIKE CONCAT('%', #{searchWord}, '%')",
            "AND state = 3"
    })
    List<Article> findByTitle(@Param("searchWord") String searchWord);


    /**
     * 根据id查询文章
     * @param articleId
     * @return
     */
    @Select("SELECT * FROM article WHERE articleId = #{articleId} AND state = 3")
    Article findArticleById(@Param("articleId") int articleId);

    /**
     * 查询文章，按时间排序
     * @return
     */
    @Select("SELECT * FROM article WHERE state = 3 ORDER BY updateTime DESC")
    List<Article> findAllArticlesOrderByUpdateTime();

}
