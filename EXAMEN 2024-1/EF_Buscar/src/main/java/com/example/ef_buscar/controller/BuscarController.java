package com.example.ef_buscar.controller;

import com.example.ef_buscar.client.ListarServiceClient;
import com.example.ef_buscar.entity.Employee;
import com.example.ef_buscar.repository.EmployeeRepository;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rh")
public class BuscarController {

    @Autowired
    private ListarServiceClient listarServiceClient;

    @Autowired
    private EmployeeRepository employeeRepository;

    private final Gson gson = new Gson();

    @GetMapping("/buscarempleado/{letra}/{order}")
    public ResponseEntity<String> buscarEmpleadoPorLetra(
            @PathVariable String letra,
            @PathVariable String order) {
        try {
            // 1. Llamar al microservicio EF_Listar usando Feign
            String jsonResponse = listarServiceClient.getEmployeesOrderBy(order);

            // 2. Convertir JSON a Lista de Employee usando Gson
            Type employeeListType = new TypeToken<List<Employee>>(){}.getType();
            List<Employee> allEmployees = gson.fromJson(jsonResponse, employeeListType);

            // 3. Filtrar empleados que empiecen con la letra especificada
            List<Employee> filteredEmployees = allEmployees.stream()
                    .filter(emp -> emp.getFirstName().toLowerCase().startsWith(letra.toLowerCase()))
                    .collect(Collectors.toList());

            // 4. Convertir resultado a JSON y retornar
            String finalJson = gson.toJson(filteredEmployees);

            return ResponseEntity.ok()
                    .header("Content-Type", "application/json")
                    .body(finalJson);

        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }

    // Método adicional: búsqueda directa en BD (opcional)
    @GetMapping("/buscarempleado/local/{letra}")
    public ResponseEntity<String> buscarEmpleadoLocal(@PathVariable String letra) {
        try {
            List<Employee> employees = employeeRepository.findByFirstNameStartingWith(letra);
            String jsonResponse = gson.toJson(employees);

            return ResponseEntity.ok()
                    .header("Content-Type", "application/json")
                    .body(jsonResponse);

        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }
}