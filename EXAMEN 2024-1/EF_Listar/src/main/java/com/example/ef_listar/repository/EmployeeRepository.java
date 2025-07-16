package com.example.ef_listar.repository;

import com.example.ef_listar.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {

    // Método para ordenar por firstName
    @Query("SELECT e FROM Employee e ORDER BY e.firstName ASC")
    List<Employee> findAllOrderByFirstName();

    // Método para ordenar por lastName
    @Query("SELECT e FROM Employee e ORDER BY e.lastName ASC")
    List<Employee> findAllOrderByLastName();

    // Método para ordenar por email
    @Query("SELECT e FROM Employee e ORDER BY e.email ASC")
    List<Employee> findAllOrderByEmail();

    // Método genérico para ordenar por cualquier campo
    @Query("SELECT e FROM Employee e ORDER BY " +
            "CASE WHEN :order = 'firstname' THEN e.firstName " +
            "WHEN :order = 'lastname' THEN e.lastName " +
            "WHEN :order = 'email' THEN e.email END ASC")
    List<Employee> findAllOrderByField(@Param("order") String order);
}