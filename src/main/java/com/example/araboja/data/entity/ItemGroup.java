package com.example.araboja.data.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

// 품종
@Entity
@Getter
@Setter
public class ItemGroup {

    @Id
    Long ItemGroupCode;

    String ItemGroupName;

}
