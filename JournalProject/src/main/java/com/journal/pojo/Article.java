package com.journal.pojo;

import lombok.Data;

@Data
public class Article {
    private int articleID;
    private int userID;
    private String title;
    private String filepath;
    private int categoryID;
    private String upLoadTime;
    private String updateTime;
    private int state;
    private String keywords;
    private int count;

}
