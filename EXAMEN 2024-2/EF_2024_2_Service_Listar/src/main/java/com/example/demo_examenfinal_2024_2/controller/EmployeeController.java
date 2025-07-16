package com.example.demo_examenfinal_2024_2.controller;

import com.example.demo_examenfinal_2024_2.entity.Employee;
import com.example.demo_examenfinal_2024_2.repository.EmployeeRepository;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
public class EmployeeController {

    final EmployeeRepository employeeRepository;

    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    // PREGUNTA 1: Listar empleados ordenados por campo
    @GetMapping(value="/rh/empleado/orderby/{order}", produces="application/json")
    public List<Employee> listaEmpleados(@PathVariable("order") String ordercolum) {

        // Mapear nombres de columnas como en JobController
        if (ordercolum.toLowerCase().equals("firstname")) {
            ordercolum = "firstName";
        } else if (ordercolum.toLowerCase().equals("lastname")) {
            ordercolum = "lastName";
        } else if (ordercolum.toLowerCase().equals("jobitle")) {
            ordercolum = "jobID";
        }

        return employeeRepository.findAll(Sort.by(Sort.Direction.ASC, ordercolum));
    }

    // PREGUNTA 2: Información de empleado por ID
    @GetMapping(value="/rh/empleado/{id}", produces="application/json")
    public Employee infoEmpleados(@PathVariable("id") Integer id) {

        Optional<Employee> optEmployee = employeeRepository.findById(id);
        if (optEmployee.isPresent()) {
            return optEmployee.get();
        } else {
            return null; // O lanzar excepción según preferencia
        }
    }

    // PREGUNTA 3: Registrar nuevo empleado
    @PostMapping(value="/rh/empleado", consumes = "application/json", produces = "application/json")
    public ResponseEntity<HashMap<String, Object>> agregarEmpleado(
            @RequestBody Employee newEmployee) {

        // Guardar el empleado en la base de datos
        Employee savedEmployee = employeeRepository.save(newEmployee);

        // Crear respuesta JSON
        HashMap<String, Object> responseJson = new HashMap<>();
        responseJson.put("id", savedEmployee.getId());

        return ResponseEntity.status(HttpStatus.CREATED).body(responseJson);
    }
}