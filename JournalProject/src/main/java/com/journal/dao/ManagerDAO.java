package com.journal.dao;

import com.journal.pojo.Article;
import com.journal.pojo.Manager;
import com.journal.pojo.basicClass.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

//对应manager
@Mapper
public interface ManagerDAO {
    /**
     * 查询所有管理员
     * @return
     */
    @Select("select * from manager")
    public List<Manager> findAll();

    /**
     * 根据账户密码寻找管理员是否存在
     * @param account 账户
     * @param password  密码
     * @return 一个user类
     */
    @Select("select * from manager where account = #{account} and password = #{password}")
    public Manager findByAccount(@Param("account") String account,@Param("password") String password);

    /**
     * 修改管理员密码
     * @param account 账户
     * @param password 密码
     * @return 修改行数
     */
   @Update("UPDATE manager SET password = #{password} WHERE account = #{account}")
   public int updateByAccount(@Param("account") String account,@Param("password") String password);
}

