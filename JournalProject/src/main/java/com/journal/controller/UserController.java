package com.journal.controller;

import com.github.pagehelper.PageInfo;
import com.journal.pojo.Article;
import com.journal.pojo.basicClass.User;
import com.journal.service.UserSeries;
import com.journal.utils.ApiResponse;
import com.journal.utils.PageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserSeries userService;

    //用户登录判定 使用request获取请求体中的参数
    @RequestMapping("/login")
    public void loginUserJudge(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
        String loginAccount = request.getParameter("demo-loginAccount");
        String password = request.getParameter("demo-psw");

        User user = new User();
        User isNone;
        user.setPassword(password);

        if(loginAccount.contains("@")){
            user.setEmail(loginAccount);
            isNone = userService.findByEmailAndPassword(user);
        }else{
            user.setPhone(loginAccount);
            isNone = userService.findByPhoneAndPassword(user);
        }

        if(isNone==null){
            request.getSession().setAttribute("error","Account ont found");
            response.sendRedirect("/page/Login.html");
            return;
        }

        HttpSession session = request.getSession();
        session.setAttribute("user",isNone);
        response.sendRedirect("/page/index.html");
    }

    @RequestMapping("/findArticle")
    public ApiResponse<PageInfo> findArticle(@RequestParam("key") String key, @RequestParam("searchWord") String searchWord, PageRequest page) {
        PageInfo<Article> pageInfo = null;
        switch (key) {
            case "title":
                pageInfo = userService.findByTitle(searchWord,page);
                break;
            case "author":
                pageInfo = userService.findByAuthor(searchWord,page);
                break;
            case "category":
                pageInfo = userService.findByCategory(searchWord,page);
                break;
            case "keywords":
                pageInfo = userService.findByKeyWord(searchWord,page);
                break;
            default:
                return new ApiResponse<>(false, "Invalid search key", null);
        }

        return new ApiResponse<>(true, "Success", pageInfo);
    }
}
