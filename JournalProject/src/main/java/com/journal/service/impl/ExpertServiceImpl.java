package com.journal.service.impl;

import com.journal.dao.ArticleDao;
import com.journal.pojo.Article;
import com.journal.service.ExpertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ExpertServiceImpl implements ExpertService {

    @Autowired
    ArticleDao articleDao;
    /**
     * 查询所有未审核的文章
     * @return
     */
    @Override
    public List<Article> findState1(int categoryId) {
        return articleDao.findState1(categoryId);
    }

    /**
     * 更新文章（审核文章，是否通过审核）
     * @param articleID
     * @param state
     * @return
     */
    @Override
    public int updateArticleState(int articleID,  int state) {
        return articleDao.updateArticleState(articleID,state);
    }
}
