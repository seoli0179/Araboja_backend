package com.example.araboja.data.dto.dailySalesList;

import com.example.araboja.data.dto.dailyPriceByCategoryList.MyCondition;
import com.example.araboja.data.dto.dailyPriceByCategoryList.MyDataDetail;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DailySalesListDto {

    private List<List<String>> condition;
    private String error_code;
    private List<PriceInfo> price;

}

