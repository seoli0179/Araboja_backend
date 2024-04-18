package com.example.araboja.data.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Item {

    @Id
    String itemCode;

    String itemName;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "ItemGroupCode")
    ItemGroup itemGroup;

}
