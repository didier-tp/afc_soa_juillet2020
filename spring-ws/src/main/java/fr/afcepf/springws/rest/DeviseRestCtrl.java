package fr.afcepf.springws.rest;

import fr.afcepf.springws.entity.Devise;
import fr.afcepf.springws.service.DeviseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/devise-api/public/devise", headers="Accept=application/json")
public class DeviseRestCtrl {

    @Autowired
    private DeviseService deviseService; //ou bien deviseDao

    //http://localhost:8383/spring-ws/devise-api/public/devise/EUR
    Devise getDeviseByCode(String code){
        return null;
    }
    //http://localhost:8383/spring-ws/devise-api/public/devise
    //ou bien
    ////http://localhost:8383/spring-ws/devise-api/public/devise?changeMini=0.9
    List<Devise> getDevisesByCriteria(double changeMini){
        return null;
    }

}
