package com.example.araboja.data.dto.dailySalesList;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class PriceInfo {

    private String product_cls_code;
    private String product_cls_name;
    private String category_code;
    private String category_name;
    private String productno;
    private String lastest_day;
    private String productName;
    private String item_name;
    private String unit;
    private String day1;
    private int dpr1;
    private String day2;
    private int dpr2;
    private String day3;
    private int dpr3;
    private String day4;
    private int dpr4;
    private String direction;
    private String value;
    private double priceDiff;

}
