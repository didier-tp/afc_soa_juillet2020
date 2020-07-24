package fr.afcepf.tp;

import javax.jws.WebService;

@WebService(endpointInterface = "fr.afcepf.tp.CalculTva")
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
