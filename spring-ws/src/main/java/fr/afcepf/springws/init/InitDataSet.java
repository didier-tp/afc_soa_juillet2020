package fr.afcepf.springws.init;

import javax.annotation.PostConstruct;

import fr.afcepf.springws.entity.Devise;
import fr.afcepf.springws.service.DeviseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;



@Profile("initData")//en développement seulement , pas en production 
@Component
public class InitDataSet {
	
	@Autowired
	private DeviseService deviseService;
	
	
	@PostConstruct()
	public void initData() {
		Devise euro = deviseService.sauvegarderDevise(new Devise("EUR","euro",0.9));
		
		
		deviseService.sauvegarderDevise(new Devise("USD","dollar",1.0));
		deviseService.sauvegarderDevise(new Devise("GBP","livre",0.8));
		deviseService.sauvegarderDevise(new Devise("JPY","yen",120.0));
	}

}
