package fr.afcepf.springws.dao;

import fr.afcepf.springws.entity.Pays;
import org.springframework.data.repository.CrudRepository;

public interface PaysDao extends CrudRepository<Pays,String> {

}
