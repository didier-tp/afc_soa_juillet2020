package fr.afcepf.springws.dao;

import fr.afcepf.springws.entity.Devise;
import org.springframework.data.repository.CrudRepository;

public interface DeviseDao extends CrudRepository<Devise,String> {
    //par héritage:
    //.findById(); ,.findAll();
    //.save()
    //.deleteById()

}
