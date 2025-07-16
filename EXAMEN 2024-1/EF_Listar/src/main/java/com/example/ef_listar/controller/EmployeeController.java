package com.example.ef_listar.controller;
import com.example.ef_listar.entity.Employee;
import com.example.ef_listar.repository.EmployeeRepository;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

        import java.util.List;

@RestController
@RequestMapping("/rh")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    private final Gson gson = new Gson();

    @GetMapping("/empleado/orderby/{order}")
    public ResponseEntity<String> getEmployeesOrderBy(@PathVariable String order) {
        try {
            List<Employee> employees;

            // Validar el parámetro order
            switch (order.toLowerCase()) {
                case "firstname":
                    employees = employeeRepository.findAllOrderByFirstName();
                    break;
                case "lastname":
                    employees = employeeRepository.findAllOrderByLastName();
                    break;
                case "email":
                    employees = employeeRepository.findAllOrderByEmail();
                    break;
                default:
                    return ResponseEntity.badRequest()
                            .body("{\"error\":\"Invalid order parameter. Use: firstname, lastname, or email\"}");
            }

            // Convertir a JSON usando Gson
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