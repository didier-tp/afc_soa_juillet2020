package fr.afcepf.springws.rest;

import fr.afcepf.springws.entity.Devise;
import fr.afcepf.springws.service.DeviseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/devise-api/public/devise", headers="Accept=application/json")
public class DeviseRestCtrl {

    @Autowired
    private DeviseService deviseService; //ou bien deviseDao

    //http://localhost:8383/spring-ws/devise-api/public/devise/EUR
    @GetMapping(value="/{codeDevise}")
    Devise getDeviseByCode(@PathVariable(name="codeDevise") String code){
        return deviseService.deviseByCode(code);
        //à améliorer (404 si null)
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
