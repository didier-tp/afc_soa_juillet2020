package fr.afcepf.springws.rest;

import fr.afcepf.springws.entity.Devise;
import fr.afcepf.springws.exception.MyEntityNotFoundException;
import fr.afcepf.springws.service.DeviseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

//Embryon d'api REST avec BASIC HTTP AUTH (not token)

@RequestMapping(value = "/simple-api", headers="Accept=application/json")
public class OneCallRestCtrl {
    @Autowired
    private DeviseService deviseService; //ou bien deviseDao

    @GetMapping(value="/private/devise/{codeDevise}")
    Devise getDeviseByCode(@PathVariable(name="codeDevise") String code)
            throws MyEntityNotFoundException {
        Devise dev= deviseService.deviseByCode(code);
        if(dev==null)
            throw new MyEntityNotFoundException("aucune devise avec codeDevise="+code);
        else
            return dev;
    }
}
