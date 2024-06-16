package com.journal.utils;

import lombok.AllArgsConstructor;
import lombok.Data;

//通信标准
@Data
@AllArgsConstructor  //生成全参构造
public class ApiResponse<T> {
    private boolean success;  //请求是否成功
    private String message;    //提示数据
    private T data;             //具体数据
}
