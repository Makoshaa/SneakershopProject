package kz.bitlab.finalproject.sneakershop.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "t_sneakers")
@Getter
@Setter
public class Sneakers extends BaseModel {

    @Column(name = "name")
    private String name;

    @Column(name = "color")
    private String color;

    @Column (name = "price")
    private int price;



}
