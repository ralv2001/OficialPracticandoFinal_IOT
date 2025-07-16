package com.example.demo_examenfinal_2024_2.controller;

import com.example.demo_examenfinal_2024_2.entity.Employee;
import com.example.demo_examenfinal_2024_2.repository.EmployeeRepository;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;


//Completar lo que falta
public class EmployeeController {

    final EmployeeRepository employeeRepository;

    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @GetMapping(value="....", produces="application/json")
    public List<Employee> listaEmpleados(........ String  ordercolum)

    {
      
        return ....;
    }

    @GetMapping(value="....", produces="application/json")
    public Employee infoEmpleados(......Integer id)

    {
        return .....;
    }

    @PostMapping(........, consumes = "application/json", produces = "application/json")
    public ResponseEntity<HashMap<String, Object>> agregarEmpleado(
            @RequestBody Employee newEmployee) {
        // Lógica para agregar el empleado a la lista (o base de datos)
        employeeRepository.------(newEmployee);
        //Completar
		//Completar
		//Completar
		//Completar
		
        return ResponseEntity.status(HttpStatus.CREATED).body(responseJson);
    }




}
