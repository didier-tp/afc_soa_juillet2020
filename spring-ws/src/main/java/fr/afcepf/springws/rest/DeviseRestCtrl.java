package fr.afcepf.springws.rest;

import fr.afcepf.springws.dto.DeleteResponse;
import fr.afcepf.springws.dto.ResConv;
import fr.afcepf.springws.entity.Devise;
import fr.afcepf.springws.exception.MyAlreadyExistsException;
import fr.afcepf.springws.exception.MyEntityNotFoundException;
import fr.afcepf.springws.service.DeviseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*") //"*" : autoriser tout le monde en phase de dev
@RequestMapping(value = "/devise-api", headers="Accept=application/json")
public class DeviseRestCtrl {

    @Autowired
    private DeviseService deviseService; //ou bien deviseDao


    //http://localhost:8383/spring-ws/devise-api/private/devise/EUR appelé en DELETE
    @DeleteMapping(value="/private/devise/{codeDevise}")
    // si retour de type String+throw ou ResponseEntity<String> , "ok " ou "echec"
    // si retour de type DeleteResponse+throw ResponseEntity<DeleteResponse> , { "message" : "ok" , "success" : true}

    ResponseEntity<DeleteResponse> deleteDeviseByCodeV1(@PathVariable(name="codeDevise") String code)
            throws MyEntityNotFoundException {
        try {
            deviseService.deleteDeviseByCode(code);
            return new ResponseEntity<DeleteResponse>(
                    DeleteResponse.withSuccess("devise with code=" + code + " is successfully deleted "),
                    HttpStatus.OK);
        }catch(Exception ex){
            return new ResponseEntity<DeleteResponse>(
                    DeleteResponse.withError("devise with code=" + code + " was not found "),
                    HttpStatus.NOT_FOUND);
        }
    }
/*
    ResponseEntity<Map<String,Object>> deleteDeviseByCodeV2(@PathVariable(name="codeDevise") String code)
            throws MyEntityNotFoundException {
        Map<String,Object> deleteResponseMap = new HashMap<>();
        try {
            deviseService.deleteDeviseByCode(code);
            deleteResponseMap.put("success",true);
            deleteResponseMap.put("message","devise with code=" + code + " is successfully deleted ");
            return new ResponseEntity<Map<String,Object>>(deleteResponseMap,HttpStatus.OK);
        }catch(Exception ex){
            deleteResponseMap.put("success",false);
            deleteResponseMap.put("message","devise with code=" + code + " was not found , not deleted ");
            return new ResponseEntity<Map<String,Object>>(deleteResponseMap,HttpStatus.NOT_FOUND);
        }
    }*/

    //http://localhost:8383/spring-ws/devise-api/private/devise appelé en POST
    //avec dans la partie body de request des données json de de type
    //{ "code" : "m1" , "name" : "monnaie1" , "change" : 1.1234 }
    @PostMapping(value="/private/devise")
    Devise postDevise(@RequestBody Devise dev) {
         Devise alreadyExistingDev= deviseService.deviseByCode(dev.getCode());
         if(alreadyExistingDev!=null)
             throw new  MyAlreadyExistsException(
                     "ajout impossible , une devise existe déjà avec le code="+dev.getCode());
         deviseService.sauvegarderDevise(dev);
         return dev;
    }

    //http://localhost:8383/spring-ws/devise-api/private/devise appelé en PUT
    //avec dans la partie body de request des données json de de type
    //{ "code" : "m1" , "name" : "monnaie1_bis" , "change" : 1.12364 }
    @PutMapping(value="/private/devise")
    Devise putDevise(@RequestBody Devise dev) {
        Devise alreadyExistingDev= deviseService.deviseByCode(dev.getCode());
        if(alreadyExistingDev==null)
            throw new  MyEntityNotFoundException(
                    "modification impossible , aucune devise n'existe avec le code="+dev.getCode());
        deviseService.sauvegarderDevise(dev);
        return dev;
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

    //http://localhost:8383/spring-ws/devise-api/public/devise-convert?src=EUR&target=USD&amount=200
    @GetMapping(value="/public/devise-convert")
    ResConv convertDevises(@RequestParam(value="src",required=true) String src,
                           @RequestParam(value="target",required=true) String target,
                           @RequestParam(value="amount",required=true) Double amount){

        ResConv resConv = new ResConv();
        resConv.setSrc(src); resConv.setTarget(target); resConv.setAmount(amount);
        resConv.setResult(deviseService.convertir(amount,src,target));
        return resConv;
    }

}
