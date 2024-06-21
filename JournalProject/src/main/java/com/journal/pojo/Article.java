package com.journal.pojo;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class Article {
    private int articleID;
    private int userID;
    private String title;
    private String filepath;
    private int categoryID;
    private String upLoadTime;
    private String updateTime;
    private int state = 1;
    private String keywords;
    private int count;
    private String nickName;
    private String categoryName;
    private String Text; //文章内容
}
