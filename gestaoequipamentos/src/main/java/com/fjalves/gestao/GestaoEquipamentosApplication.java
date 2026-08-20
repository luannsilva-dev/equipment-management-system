package com.fjalves.gestao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GestaoEquipamentosApplication {

    public static void main(String[] args) {
        
        System.setProperty("jdk.tls.client.protocols", "TLSv1.2,TLSv1.3");
        
        SpringApplication.run(GestaoEquipamentosApplication.class, args);
    }
}