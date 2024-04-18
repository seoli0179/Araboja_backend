package com.example.araboja.data.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ProductRank {

    @Id
    String productRankCode;

    Long productGraderRank;

    String productRankCodeName;

}
