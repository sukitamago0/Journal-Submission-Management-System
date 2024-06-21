package com.journal.pojo.basicClass;

import lombok.Data;

@Data
public class User {
    private int userID;
    private String password;
    private String nickName;
    private int age;
    private String gender;
    private String realName; //真实姓名
    private String cardID; //身份证号
    private String debitCard; //银行卡号
    private String email;
    private String phone;
    private String creatDate;
    private int typeID = 1;  //1：普通用户   2.编辑    3.专家
    private int categoryID = 0;   //默认设为0，0在category表中不存在，即为表示没有专业领域（没有审稿权限）

    public User(int userID, String password, String nickName, int age, String gender, String realName, String cardID, String debitCard, String email, String phone, int typeID) {
        this.userID = userID;
        this.password = password;
        this.nickName = nickName;
        this.age = age;
        this.gender = gender;
        this.realName = realName;
        this.cardID = cardID;
        this.debitCard = debitCard;
        this.email = email;
        this.phone = phone;
        this.typeID = typeID;
    }

    public User(int userID, String password, String nickName, int age, String gender, String realName, String cardID, String debitCard, String email, String phone, String creatDate, int typeID) {
        this.userID = userID;
        this.password = password;
        this.nickName = nickName;
        this.age = age;
        this.gender = gender;
        this.realName = realName;
        this.cardID = cardID;
        this.debitCard = debitCard;
        this.email = email;
        this.phone = phone;
        this.creatDate = creatDate;
        this.typeID = typeID;
    }

    public User() {
    }
}

