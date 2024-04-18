package com.example.araboja.data.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Cultivar {

    @EmbeddedId
    CultivarPK cultivarCode;

    String cultivarName;

    String productRank;

}
