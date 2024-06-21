package com.journal.service;

import com.journal.pojo.Article;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ExpertService {
    List<Article> findState1(int categoryId);
    int updateArticleState(int articleID,  int state);
}
