package fr.afcepf.springws.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/*
var jsRes = {
				nbMois: nbMois, montant: montant,
				tauxInteret: tauxInteretResponseJs.tauxInteret,
				mensualite: calculMensualiteResponseJs.mensualite,
				fraisDossier: 100.0
			};
 */
@Getter @Setter
@NoArgsConstructor
@ToString
public class PropPret {
    private int nbMois;
    private double montant;
    private double tauxInteret;
    private double mensualite;
    private double fraisDossier;

}
