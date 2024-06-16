package com.journal.pojo;

import com.journal.pojo.basicClass.User;

public class Editor extends User {
    public Editor(int userID, String password, String nickName, int age, String gender, String realName, String cardID, String debitCard, String email, String phone, int typeID) {
        super(userID, password, nickName, age, gender, realName, cardID, debitCard, email, phone, typeID);
    }
    //编辑类

}
