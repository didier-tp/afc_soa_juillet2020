package fr.afcepf.springws.service;

import fr.afcepf.springws.entity.Devise;

import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.List;

@WebService
public interface DeviseService {

     List<Devise> allDevises();
     List<Devise> devisesByChangeMini(@WebParam(name="changeMini")double changeMini);
     Devise deviseByCode(@WebParam(name="code")String code);
     Devise sauvegarderDevise(@WebParam(name="devise") Devise devise);//save or update
     double convertir(@WebParam(name="montant")double montant,
                      @WebParam(name="codeDeviseSource")String codeDeviseSource,
                      @WebParam(name="codeDeviseCible")String codeDeviseCible);
}
