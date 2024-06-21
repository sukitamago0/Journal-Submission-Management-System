package com.journal.controller;

import com.journal.pojo.Article;
import com.journal.pojo.Manager;
import com.journal.pojo.basicClass.User;
import com.journal.service.ExpertService;
import com.journal.service.ManageService;
import com.journal.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/expert")
public class ExpertController {
    @Autowired
    private ExpertService expertService;

    // 获取所有未审核过的文章
    @RequestMapping("/findAll")
    public ApiResponse<List<Article>> findAllUnreviewedArticles(HttpServletRequest request) {
        //从session中获取用户
        User user = (User) request.getSession().getAttribute("user");
        //验证是否为专家身份(是否有审稿权限)
        if (user.getTypeID() == 3) {
            //传入专家的专业领域id
            List<Article> articles = expertService.findState1(user.getCategoryID());
            return new ApiResponse<>(true, "Success", articles);
        }
        return new ApiResponse<>(false, "Fail! You don't have access ", null);
    }

    // 审核文章api
    @RequestMapping("/audit")
    public ApiResponse<Integer> auditArticle(@RequestParam int articleID, @RequestParam int state) {
        int i = expertService.updateArticleState(articleID, state);
        if (i == 1){
            return new ApiResponse<>(true, "Article audit successfully.", i);
        }
        return new ApiResponse<>(true, "Failed to audit the article. Please contact the manager.", i);
    }
}
