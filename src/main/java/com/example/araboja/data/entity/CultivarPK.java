package com.example.araboja.data.entity;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class CultivarPK implements Serializable {

    private String cultivarCode;
    private Long itemCode;

}
