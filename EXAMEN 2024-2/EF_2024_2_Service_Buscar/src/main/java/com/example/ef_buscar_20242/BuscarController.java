package com.example.ef_buscar_20242;

import com.example.ef_buscar_20242.entity.Employee;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@RestController
public class BuscarController {

    @Autowired
    private ServiceBuscarClient serviceBuscarClient;

    @GetMapping(value="/rh/filtarempleado/{letra}/{order}", produces="application/json")
    public String listarBuscar(@PathVariable("letra") String item_a,
                               @PathVariable("order") String item_b) {

        ArrayList<Employee> buscarempleado = new ArrayList<>();

        // Llamar al microservicio EF_Listar usando Feign
        String response = serviceBuscarClient.listar(item_b);

        // Convertir JSON a lista de empleados usando Gson
        Gson gson = new Gson();
        Type listType = new TypeToken<List<Employee>>(){}.getType();
        List<Employee> empleados = gson.fromJson(response, listType);

        // Filtrar empleados que empiecen con la letra especificada
        for (Employee emp : empleados) {
            if (emp.getFirstName() != null &&
                    emp.getFirstName().toLowerCase().startsWith(item_a.toLowerCase())) {
                buscarempleado.add(emp);
            }
        }

        return gson.toJson(buscarempleado);
    }
}