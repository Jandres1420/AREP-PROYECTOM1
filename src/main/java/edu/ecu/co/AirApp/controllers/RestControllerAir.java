package edu.ecu.co.AirApp.controllers;

import com.fasterxml.jackson.databind.node.NumericNode;
import edu.ecu.co.AirApp.utils.MultipleThreads;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
@RequestMapping("/airCondition")
public class RestControllerAir {


    @GetMapping("/{lat}/{lon}")
    public ResponseEntity<?> getCountry(@PathVariable String lat,@PathVariable String lon){
        String hola = "";
     //   ExecutorService numeroDeHilos = Executors.newFixedThreadPool(1000);
        MultipleThreads multipleThreads;
        for(int i = 0 ; i<20;i++){
            multipleThreads = new MultipleThreads(lat,lon);
            multipleThreads.run();
            hola+=multipleThreads.getAll();
        }

        return new ResponseEntity<>(hola, HttpStatus.OK);
    }


}
