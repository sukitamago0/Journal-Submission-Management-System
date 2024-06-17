package com.journal.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.journal.dao.ManagerDAO;
import com.journal.pojo.Manager;
import com.journal.service.ManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManageServiceImpl implements ManageService {

    @Autowired
    ManagerDAO managerDAO;

    //根据account查找
    @Override
    public Manager findByAccount(String account, String password) {
        return managerDAO.findByAccount(account, password);

    }

    //更新密码
    @Override
    public int updateByAccount(String account, String password) {
        return managerDAO.updateByAccount(account,password);
    }

    //分页获取所有管理员
    @Override
    public PageInfo<Manager> findAll(int pageNum, int pageSize) {
        // 设置分页参数
        PageHelper.startPage(pageNum, pageSize);
        // 执行查询并返回结果列表
        List<Manager> list = managerDAO.findAll();
        return new PageInfo<>(list);
    }


}

