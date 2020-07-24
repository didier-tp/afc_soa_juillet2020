package fr.afcepf.springws.dao;

import fr.afcepf.springws.entity.Devise;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DeviseDao extends CrudRepository<Devise,String> {
    //par héritage:
    //.findById(); ,.findAll();
    //.save()
    //.deleteById()

    //la query SQL ou JPAQL sera automatiquement générée via des conventions
    //de nom sur la méthode de recherche (WHERE change >= ?)
    List<Devise> findByChangeGreaterThanEqual(double tauxChangeMini);

    //sur la classe Devise (@Entity) on ajoute
    // @NamedQuery(name="Devise.rechercherSelonChangeMiniQueJaime",
    //              query="select d from Devise d WHERE d.change >= ?")
    //List<Devise> rechercherSelonChangeMiniQueJaime(double tauxChangeMini);

}
