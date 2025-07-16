package com.example.ef_buscar_20242;

import com.example.ef_buscar_20242.entity.Employee;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

#Completar ...
public class BuscarController {

    @Autowired
    private ServiceBuscarClient serviceBuscarClient;


    @GetMapping(value="/...modificar.../{word}/{order}",  produces="application/json")
    public String listarBuscar(@PathVariable("word") String item_a,
                                @PathVariable("order") String item_b)
    { 

        ArrayList<Employee> buscarempleado = new ArrayList<>();
   
	// ...
	// ...
	// ...
	// ...
 
        return gson.toJson(buscarempleado);


    }
}
