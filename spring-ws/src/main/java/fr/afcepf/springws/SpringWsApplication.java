package fr.afcepf.springws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SpringWsApplication {

    public static void main(String[] args) {

        //SpringApplication.run(MySpringBootApplication.class, args);
        SpringApplication app = new SpringApplication(SpringWsApplication.class);
        app.setAdditionalProfiles("initData","swagger");
        ConfigurableApplicationContext context = app.run(args);
        System.out.println("http://localhost:8383/spring-ws");
    }

}
