package fr.afcepf.springws.service;

import fr.afcepf.springws.dto.PropPret;

/*
  interface d'un service délégué
  ( Design pattern "Business Delegate" (design pas terne))
 */
public interface PropPretService {
      PropPret recupererPropPret(double montant, int nbMois);
      //...
}
