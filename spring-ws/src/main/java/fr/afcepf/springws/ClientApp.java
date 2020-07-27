package fr.afcepf.springws;

import fr.afcepf.springws.entity.Devise;
import org.springframework.web.client.RestTemplate;

//Cette classe serait idéalement placée dans un autre projet indépendant
// codé avec Spring
public class ClientApp {

    public static void main(String[] args) {
         RestTemplate restTemplate = new RestTemplate();
         String wsUrlPrivate = "http://localhost:8383/spring-ws/devise-api/private/devise";

         Devise nouvelleDevise = new Devise("m2" ,"Monnaie2LeRetour" , 1.12345);
         Devise nouvelleDeviseSauvegardee =
                 restTemplate.postForObject(wsUrlPrivate,nouvelleDevise ,Devise.class);
         System.out.println("nouvelleDeviseSauvegardee="+nouvelleDeviseSauvegardee);
        try {
            nouvelleDeviseSauvegardee =
                    restTemplate.postForObject(wsUrlPrivate, nouvelleDevise, Devise.class);
            System.out.println("nouvelleDeviseSauvegardee=" + nouvelleDeviseSauvegardee);
        }catch(Exception ex){
            System.err.println("echec normal" + ex.getMessage());
        }
         String wsUrlGet = "http://localhost:8383/spring-ws/devise-api/public/devise/EUR";
         String deviseEuroAsJsonString = restTemplate.getForObject(wsUrlGet, String.class);
         System.out.println("deviseEuroAsJsonString="+deviseEuroAsJsonString);
         Devise deviseEuro = restTemplate.getForObject(wsUrlGet, Devise.class);
         System.out.println("deviseEuro="+deviseEuro);


    }
}
