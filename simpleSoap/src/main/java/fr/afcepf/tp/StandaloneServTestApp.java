package fr.afcepf.tp;

import javax.xml.ws.Endpoint;

public class StandaloneServTestApp {

    public static void main(String[] args) {
        String address = "http://" + "FORM292" + ":8080/ws/tva";
        CalculTvaImpl implWs = new CalculTvaImpl();
        System.out.println("url_wsdl="+address+"?wsdl");
        Endpoint.publish(address,implWs); //ça démarre un thread en tache de fond
                                          //qui attend les requêtes à traiter
        try {
            Thread.sleep(15*60*1000);//15mn
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.exit(0);
    }
}
