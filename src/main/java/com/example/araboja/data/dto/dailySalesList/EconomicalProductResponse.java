package com.example.araboja.data.dto.dailySalesList;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EconomicalProductResponse {

    private String productName;
    private String productNo;
    private String unit;
    private int price;
    private int priceLastYear;
    private double priceChangeRate;

}
