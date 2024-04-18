package com.example.araboja.data.dto.dailyPriceByCategoryList;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DailyPriceByCategoryListDto {

    private List<MyCondition> condition;
    private MyDataDetail data;

}

