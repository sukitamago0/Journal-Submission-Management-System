package com.journal.pojo;

import com.journal.pojo.basicClass.User;

public class Expert extends User {
    public Expert(int userID, String password, String nickName, int age, String gender, String realName, String cardID, String debitCard, String email, String phone, int typeID) {
        super(userID, password, nickName, age, gender, realName, cardID, debitCard, email, phone, typeID);
    }
    //专家类

}
