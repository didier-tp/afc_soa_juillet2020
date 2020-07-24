package fr.afcepf.springws.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Devise {
    @Id
    private String code; //"EUR" , "USD"
    private String name; // "euro" , "dollar"
    private double change; //nb unite pour 1 euro

    public Devise(String code,String name,double change ) {
        this.code = code;
        this.name = name;
        this.change = change;
    }
}
