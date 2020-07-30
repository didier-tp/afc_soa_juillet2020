package fr.afcepf.springws.delegate;

import fr.afcepf.springws.dto.PropPret;
import fr.afcepf.springws.service.PropPretService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

//Ce code ne fonctionnera (temporairement) que si :
// ws 8282 sont démarrés
// orchestrateur 8484 démarré

@Component
public class TestBusinessDelegate {

    @Autowired
    private PropPretService  propPretService;

    @PostConstruct
    public void test300kmhJeudi(){
        PropPret propPret =
                propPretService.recupererPropPret(20000,120);
        System.out.println("propPret="+propPret.toString());
    }

}
