package fr.afcepf.springws.rest;

import fr.afcepf.springws.entity.Devise;
import fr.afcepf.springws.exception.MyEntityNotFoundException;
import fr.afcepf.springws.service.DeviseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/devise-api/public/devise", headers="Accept=application/json")
public class DeviseRestCtrl {

    @Autowired
    private DeviseService deviseService; //ou bien deviseDao

    //http://localhost:8383/spring-ws/devise-api/public/devise/EUR
    @GetMapping(value="/{codeDevise}")
    /*
    ResponseEntity<Devise> getDeviseByCodeV1(@PathVariable(name="codeDevise") String code){
        Devise dev = deviseService.deviseByCode(code);
        if(dev!=null)
            return new ResponseEntity<Devise>(dev, HttpStatus.OK);
        else
            return new ResponseEntity<Devise>(HttpStatus.NOT_FOUND);//404
    }
     */

    Devise getDeviseByCodeV2(@PathVariable(name="codeDevise") String code)
       throws MyEntityNotFoundException{
        Devise dev= deviseService.deviseByCode(code);
        if(dev==null)
            throw new MyEntityNotFoundException("aucune devise avec codeDevise="+code);
        else
            return dev;
    }

    //http://localhost:8383/spring-ws/devise-api/public/devise
    //ou bien
    ////http://localhost:8383/spring-ws/devise-api/public/devise?changeMini=0.9
    @GetMapping(value="")
    List<Devise> getDevisesByCriteria(@RequestParam(value="changeMini",required=false)
                                              Double changeMini){
        if(changeMini==null) {
            return deviseService.allDevises();
        }
        else {
            return deviseService.devisesByChangeMini(changeMini);
        }
    }

}
