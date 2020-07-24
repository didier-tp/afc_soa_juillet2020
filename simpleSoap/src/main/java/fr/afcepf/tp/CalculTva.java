package fr.afcepf.tp;

import javax.jws.WebParam;
import javax.jws.WebService;

//targetNamespace par defaut : "http://tp.afcepf.fr/" (package à l'envers)
@WebService
public interface CalculTva {
    double tva(@WebParam(name="ht") double ht ,
               @WebParam(name="taux")double taux);
    double ttc(@WebParam(name="ht")double ht ,
               @WebParam(name="taux")double taux);
    String getAuthorName();
}
