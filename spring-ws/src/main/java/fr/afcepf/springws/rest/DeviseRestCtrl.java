package fr.afcepf.springws.rest;

import fr.afcepf.springws.dto.DeleteResponse;
import fr.afcepf.springws.entity.Devise;
import fr.afcepf.springws.exception.MyEntityNotFoundException;
import fr.afcepf.springws.service.DeviseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/devise-api", headers="Accept=application/json")
public class DeviseRestCtrl {

    @Autowired
    private DeviseService deviseService; //ou bien deviseDao


    //http://localhost:8383/spring-ws/devise-api/private/devise/EUR appelé en DELETE
    @...
    // si retour de type String+throw ou ResponseEntity<String> , "ok " ou "echec"
    // si retour de type DeleteResponse+throw ResponseEntity<DeleteResponse> , { "message" : "ok" , "success" : "ok}
    ResponseEntity<DeleteResponse> deleteDeviseByCode(@PathVariable(name="codeDevise") String code)
            throws MyEntityNotFoundException {
       return ...;
    }

    //http://localhost:8383/spring-ws/devise-api/private/devise appelé en POST
    //avec dans la partie body de request des données json de de type
    //{ "code" : "m1" , "name" : "monnaie1" , "change" : 1.1234 }
    @...
    Devise postDevise(@....() Devise dev) {

    }

    //http://localhost:8383/spring-ws/devise-api/private/devise appelé en PUT
    //avec dans la partie body de request des données json de de type
    //{ "code" : "m1" , "name" : "monnaie1" , "change" : 1.1234 }
    @...
    Devise putDevise(@....() Devise dev) {
        //renvoyer exception si monnaie à modifier inexistante
    }

    //http://localhost:8383/spring-ws/devise-api/public/devise/EUR
    @GetMapping(value="/public/devise/{codeDevise}")
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
    ////http://localhost:8383/spring-ws/devise-api/public/devise?name=Euro
    ////http://localhost:8383/spring-ws/devise-api/public/devise?changeMini=0.9&name=Euro
    @GetMapping(value="/public/devise")
    List<Devise> getDevisesByCriteria(@RequestParam(value="changeMini",required=false)
                                              Double changeMini,
                                      @RequestParam(value="name",required=false)
                                              String name){
        if(changeMini==null) {
            return deviseService.allDevises();
        }
        else {
            return deviseService.devisesByChangeMini(changeMini);
        }
    }

}
