package fr.afcepf.springws.delegate;

import fr.afcepf.springws.dto.PropPret;
import fr.afcepf.springws.service.PropPretService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

//@Component
@Service
public class PropPretServiceDelegate implements PropPretService {

    private RestTemplate restTemplate = new RestTemplate();

    @Override
    public PropPret recupererPropPret(double montant, int nbMois) {

        //../orchestrateur-api/public/propositionPret?nbMois=120&montant=20000
        String wsUrl =
                "http://localhost:8484/orchestrateur-api/public/propositionPret?nbMois="
                +nbMois+"&montant="+montant;
        return this.restTemplate.getForObject(wsUrl,PropPret.class);
    }
}
