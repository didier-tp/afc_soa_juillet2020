package fr.afcepf.springws.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name="pays")
public class Pays {
    @Id
    private String code;
    private String nom;
    private String capitale;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="devise") //colonne clef etrangère
                               //de la table pays référencant une devise
    private Devise devise;
    //...
    public Pays(String code,String nom,String capitale ) {
        this.code = code;
        this.nom = nom;
        this.capitale = capitale;
    }

}
