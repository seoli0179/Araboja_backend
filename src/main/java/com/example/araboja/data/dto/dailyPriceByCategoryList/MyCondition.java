package com.example.araboja.data.dto.dailyPriceByCategoryList;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MyCondition {
    private String p_product_cls_code;
    private List<String> p_country_code;
    private String p_regday;
    private String p_convert_kg_yn;
    private String p_category_code;
    private String p_cert_key;
    private String p_cert_id;
    private String p_returntype;

    // Getters and setters
}
