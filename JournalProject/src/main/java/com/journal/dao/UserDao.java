package com.journal.dao;

import com.journal.pojo.basicClass.User;
import org.apache.ibatis.annotations.*;

//对应表user
@Mapper
public interface UserDao {
    @Select("select * from user where phone = #{phone}")
    public User findUserByPhone(@Param("phone")String phone);

    @Select("select * from user where email = #{email}")
    public User findUserByEmail(@Param("email")String email);

    /**
     * 根据手机与密码查找user（手机号登录）
     * @param Phone
     * @param password
     * @return
     */
    @Select("select * from user where phone = #{phone} and password = #{password}")
    public User findByPhoneAndPassword(@Param("phone")String Phone,@Param("password")String password);

    /**
     * 根据邮箱与密码查找user（邮箱登录）
     * @param email
     * @param password
     * @return
     */
    @Select("select * from user where email = #{email} and password = #{password}")
    public User findByEmailAndPassword(@Param("email")String email,@Param("password")String password);

    /**
     * 新建用户
     * @param user
     * @return
     */
    @Insert("INSERT INTO user (nickName, password,email, phone,typeID,creatDate,gender) VALUES (#{nickName}, #{password}, #{email}, #{phone},#{typeID},NOW(),#{gender})")
    int insertUser(User user);

    /**
     * 根据userID更改用户信息
     * @param user
     * @return
     */
    @Update("UPDATE user SET password = #{password}, nickName = #{nickName}, age = #{age}, gender = #{gender}, realName = #{realName}, " +
            "cardID = #{cardID}, debitCard = #{debitCard}, email = #{email}, phone = #{phone}, typeID = #{typeID} " +
            "WHERE userID = #{userID}")
    int updateUser(User user);

    /**
     * 根据ID查找User
     * @param userID
     * @return
     */
    @Select("select * from user where userID = #{userID}")
    public User findByID(@Param("userID")String userID);


}

