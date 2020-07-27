package fr.afcepf.springws.service;

import fr.afcepf.springws.dao.DeviseDao;
import fr.afcepf.springws.entity.Devise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.jws.WebService;
import java.util.ArrayList;
import java.util.List;

@Service
@WebService(endpointInterface = "fr.afcepf.springws.service.DeviseService")
@Transactional //pour demander à Spring de gérer les commit/rollback
public class DeviseServiceImpl implements DeviseService{

    @Autowired
    private DeviseDao deviseDao;

    @Override
    public List<Devise> allDevises() {
        return (List<Devise>)deviseDao.findAll();
    }

    @Override
    public List<Devise> devisesByChangeMini(double changeMini) {
        return deviseDao.findByChangeGreaterThanEqual(changeMini);
    }

    @Override
    public Devise deviseByCode(String code) {
        return deviseDao.findById(code).orElse(null);
        //ou .get() pour renvoyer une exception si pas trouvé
    }

    @Override
    public Devise sauvegarderDevise(Devise devise) {
        return deviseDao.save(devise);
    }

    @Override
    public double convertir(double montant, String codeDeviseSource, String codeDeviseCible) {
        Devise deviseSource = deviseDao.findById(codeDeviseSource).get();
        Devise deviseCible = deviseDao.findById(codeDeviseCible).get();
        return montant * deviseCible.getChange() / deviseSource.getChange(); //ou ???
    }

    @Override
    public void deleteDeviseByCode(String code) {
        deviseDao.deleteById(code);
    }
}
