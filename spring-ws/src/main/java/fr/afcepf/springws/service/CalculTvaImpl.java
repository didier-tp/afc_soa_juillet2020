package fr.afcepf.springws.service;

import org.springframework.stereotype.Service;

import javax.jws.WebService;
//@WebService SOAP
@WebService(endpointInterface = "fr.afcepf.springws.service.CalculTva")
@Service //composant Spring
public class CalculTvaImpl  implements CalculTva {
    public double tva(double ht, double taux) {
        return ht*taux;
    }

    public double ttc(double ht, double taux) {
        return ht*(1+taux);
    }

    public String getAuthorName() {
        return "didier";
    }
}
