package com.journal.dao;

import com.journal.pojo.Article;
import com.journal.pojo.Category;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ArticleDao {

    /**
     * 根据搜索词查询书籍以||分割（科幻||玄幻）
     * @param searchWord 搜索词
     * @return 符合条件的文章列表
     */
    @Select({
            "SELECT a.*, u.nickName, c.categoryName",
            "FROM article a",
            "JOIN user u ON a.userID = u.userID",
            "JOIN category c ON a.categoryID = c.categoryID",
            "WHERE (userID IN (SELECT userID FROM user WHERE nickName LIKE CONCAT('%', #{searchWord}, '%'))",
            "OR title LIKE CONCAT('%', #{searchWord}, '%')",
            "OR keywords LIKE CONCAT('%', #{searchWord}, '%')",
            "OR categoryID IN (SELECT categoryID FROM category WHERE categoryName LIKE CONCAT('%', #{searchWord}, '%')))",
            "AND a.state = 3"
    })
    List<Article> findArticles(@Param("searchWord") String searchWord);

    /**
     * 根据作者搜索文章
     * @param searchWord 作者昵称搜索词
     * @return 符合条件的文章列表
     */
    @Select({
            "SELECT a.*, u.nickName, c.categoryName",
            "FROM article a",
            "JOIN user u ON a.userID = u.userID",
            "JOIN category c ON a.categoryID = c.categoryID",
            "WHERE (u.nickName LIKE CONCAT('%', #{searchWord}, '%'))",
            "AND a.state = 3"
    })
    List<Article> findByAuthor(@Param("searchWord") String searchWord);

    /**
     * 根据类别搜索文章
     * @param searchWord 类别名称搜索词
     * @return 符合条件的文章列表
     */
    @Select({
            "SELECT a.*, u.nickName, c.categoryName",
            "FROM article a",
            "JOIN user u ON a.userID = u.userID",
            "JOIN category c ON a.categoryID = c.categoryID",
            "WHERE c.categoryID IN (SELECT categoryID FROM category WHERE categoryName LIKE CONCAT('%', #{searchWord}, '%'))",
            "AND a.state = 3"
    })
    List<Article> findByCategory(@Param("searchWord") String searchWord);

    /**
     * 根据关键词搜索文章
     * @param searchWord 关键词搜索词
     * @return 符合条件的文章列表
     */
    @Select({
            "SELECT a.*, u.nickName, c.categoryName",
            "FROM article a",
            "JOIN user u ON a.userID = u.userID",
            "JOIN category c ON a.categoryID = c.categoryID",
            "WHERE keywords LIKE CONCAT('%', #{searchWord}, '%')",
            "AND a.state = 3"
    })
    List<Article> findByKeyWord(@Param("searchWord") String searchWord);

    /**
     * 根据题目搜索文章
     * @param searchWord 题目搜索词
     * @return 符合条件的文章列表
     */
    @Select({
            "SELECT a.*, u.nickName, c.categoryName",
            "FROM article a",
            "JOIN user u ON a.userID = u.userID",
            "JOIN category c ON a.categoryID = c.categoryID",
            "WHERE title LIKE CONCAT('%', #{searchWord}, '%')",
            "AND a.state = 3"
    })
    List<Article> findByTitle(@Param("searchWord") String searchWord);


    /**
     * 根据id查询文章
     * @param articleId 文章ID
     * @return 对应ID的文章对象
     */
    @Select("SELECT a.*, u.nickName, c.categoryName FROM article a JOIN user u ON a.userID = u.userID JOIN category c ON a.categoryID = c.categoryID WHERE a.articleId = #{articleId}")
    Article findArticleById(@Param("articleId") int articleId);

    /**
     * 查询所有文章，并按照更新时间降序排序
     * @return 所有文章列表
     */
    @Select("SELECT a.*, u.nickName, c.categoryName FROM article a JOIN user u ON a.userID = u.userID JOIN category c ON a.categoryID = c.categoryID WHERE a.state = 3 ORDER BY updateTime DESC")
    List<Article> findAllArticlesOrderByUpdateTime();

    /**
     * 根据类别名称获取类别id
     * @param categoryName
     * @return
     */

    @Select("SELECT categoryID FROM category WHERE categoryName = #{categoryName}")
    int findCategoryIdByName(@Param("categoryName") String categoryName);

    /**
     * 首次存储文章
     *
     * @param article 文章对象
     * @return
     */
    @Insert("INSERT INTO article (userID, title, filepath, categoryID, upLoadTime, updateTime, state, keywords, count) " +
            "VALUES (#{userID}, #{title}, #{filepath}, #{categoryID}, now(), now(), #{state}, #{keywords}, #{count})")
    int saveArticle(Article article);

    /**
     * 更新文章
     * @param article
     * @return
     */
    @Update("UPDATE article SET title = #{title}, filepath = #{filepath}, categoryID = #{categoryID}, updateTime = now(), state = #{state}, keywords = #{keywords}, count = #{count} WHERE articleID = #{articleID}")
    int updateArticle(Article article);

    /**
     * 更新文章count属性（使count+1）
     * @param article
     * @return
     */
    @Update("UPDATE article SET count = #{count} WHERE articleID = #{articleID}")
    int updateArticleCount(Article article);


    /**
     *更新文章状态
     * @param articleID
     * @param state
     * @return
     */
    @Update("UPDATE article SET state = #{state}, updateTime = now() WHERE articleID = #{articleID}")
    int updateArticleState(@Param("articleID") int articleID, @Param("state") int state);

    /**
     * 查询所有类别
     * @return
     */
    @Select("SELECT * FROM category")
    List<Category> findAllCategory();

    /**
     * 根据审稿人专业领域，查询所有未审核的文章
     * @return
     */
    @Select("SELECT a.*, u.nickName, c.categoryName FROM article a " +
            "JOIN user u ON a.userID = u.userID " +
            "JOIN category c ON a.categoryID = c.categoryID " +
            "WHERE a.state = 1 AND a.categoryid = #{categoryId}")
    List<Article> findState1(int categoryId);


    /**
     * 根据用户ID查询文章
     * @param userId 用户ID
     * @return 符合条件的文章列表
     */
    @Select({
            "SELECT a.*, u.nickName, c.categoryName",
            "FROM article a",
            "JOIN user u ON a.userID = u.userID",
            "JOIN category c ON a.categoryID = c.categoryID",
            "WHERE a.userID = #{userId}"
    })
    List<Article> findByUserId(@Param("userId") int userId);

    /**
     * 按count（点击次数）大小排序查询文章
     * @return
     */
    @Select("SELECT a.*, u.nickName, c.categoryName " +
            "FROM article a " +
            "JOIN user u ON a.userID = u.userID " +
            "JOIN category c ON a.categoryID = c.categoryID " +
            "WHERE a.state = 3 " +
            "ORDER BY a.count DESC")
    List<Article> findByCount();
}
