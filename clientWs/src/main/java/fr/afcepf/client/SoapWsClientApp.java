package fr.afcepf.client;


import fr.afcepf.tp.CalculTva;
import fr.afcepf.tp.CalculTvaImplService;

import java.net.MalformedURLException;
import java.net.URL;

public class SoapWsClientApp {

    public static void main(String[] args) {
        //String serverHostName="form291"; //machine de francois
        //String serverHostName="form290"; //machine de Thomas
        String serverHostName="form292"; //machine de didier
        String wsdlUrlAsString="http://"+serverHostName+":8080/ws/tva?wsdl";
        URL wsdl_url = null;
        try {
            wsdl_url = new URL(wsdlUrlAsString);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        CalculTvaImplService s = new CalculTvaImplService(wsdl_url);//généré par wsimport
        CalculTva wsProxy = s.getCalculTvaImplPort();//.get....Port()
        String a=wsProxy.getAuthorName();
        System.out.println("a="+a);
        System.out.println("ttc pour ht=200 et taux=0.2 ="+wsProxy.ttc(200,0.2));
    }
}
