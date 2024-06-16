package com.journal.service;

import com.github.pagehelper.PageInfo;
import com.journal.pojo.Article;
import com.journal.pojo.Manager;
import com.journal.pojo.basicClass.User;
import org.apache.ibatis.annotations.Param;

public interface ManageService {
    public Manager findByAccount(String account,String password);
    public int updateByAccount(String account,String password);
    public PageInfo<Manager>findAll(int pageNum, int pageSize);
}
