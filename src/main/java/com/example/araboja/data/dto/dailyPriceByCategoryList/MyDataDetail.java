package com.example.araboja.data.dto.dailyPriceByCategoryList;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MyDataDetail {
    private String error_code;
    private List<MyItem> item;

    // Getters and setters
}
