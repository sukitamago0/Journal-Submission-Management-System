package com.journal.utils;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

//分页参数
@Data
public class PageRequest {
    @JsonProperty(defaultValue = "1")
    private int pageNum = 1;

    @JsonProperty(defaultValue = "10")
    private int pageSize = 10;

    // Getters and setters
}