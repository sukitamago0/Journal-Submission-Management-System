package com.journal.controller;

import com.github.pagehelper.PageInfo;
import com.journal.utils.ApiResponse;
import com.journal.pojo.Manager;
import com.journal.service.ManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/managers")
public class ManagerController {
    @Autowired
    private ManageService manageService;

    /**
     * 更新管理员密码
     * @return
     */
    @RequestMapping("/update")
    public ApiResponse<Void> updatePassword(@RequestBody Manager manager) {
        int rowsAffected = manageService.updateByAccount(manager.getAccount(), manager.getPassword());
        if (rowsAffected > 0) {
            return new ApiResponse<>(true, "Password updated successfully", null);
        } else {
            return new ApiResponse<>(false, "Manager with account " + manager.getAccount() + " not found", null);
        }
    }

    /**
     * 登录
     * @param m
     * @return
     */
    @RequestMapping("/login")
    public ApiResponse<Manager> login(@RequestBody Manager m, HttpServletRequest request) {
        Manager manager = manageService.findByAccount(m.getAccount(), m.getPassword());
        if (manager != null) {
            //存储管理员登录session
            request.getSession().setAttribute("manager",manager);
            return new ApiResponse<>(true, "Success", manager);
        } else {
            return new ApiResponse<>(false, "Manager not found", null);
        }
    }

    /**
     * 分页获取管理员
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping
    public ApiResponse<PageInfo<Manager>> findAll(@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "10") int pageSize) {
        PageInfo<Manager> pageInfo = manageService.findAll(pageNum, pageSize);
        return new ApiResponse<>(true, "Success", pageInfo);
    }
}
