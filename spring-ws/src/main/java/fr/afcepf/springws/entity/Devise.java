package fr.afcepf.springws.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name="devise")
public class Devise {
    @Id
    private String code; //"EUR" , "USD"
    private String name; // "euro" , "dollar"
    @Column(name="e_change")
    private double change; //nb unite pour 1 dollar

    @OneToMany(fetch = FetchType.LAZY , mappedBy = "devise")
    @JsonIgnore //pour limiter la serialisation "java" --> "json"
    private List<Pays> pays;

    public void addPays(Pays p){
        if(this.pays==null){
            pays = new ArrayList<>();
        }
        this.pays.add(p);
    }

    public Devise(String code,String name,double change ) {
        this.code = code;
        this.name = name;
        this.change = change;
    }
}
