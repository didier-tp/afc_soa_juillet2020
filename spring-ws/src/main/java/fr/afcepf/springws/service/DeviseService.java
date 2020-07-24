package fr.afcepf.springws.service;

import fr.afcepf.springws.entity.Devise;

import javax.jws.WebService;
import java.util.List;

@WebService
public interface DeviseService {

     List<Devise> allDevises();
     Devise deviseByCode(String code);
     Devise sauvegarderDevise(Devise devise);//save or update
     double convertir(double montant, String codeDeviseSource,String codeDeviseCible);
}
