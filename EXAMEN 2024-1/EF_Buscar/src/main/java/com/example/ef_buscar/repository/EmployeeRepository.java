package com.example.ef_buscar.repository;

import com.example.ef_buscar.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {

    // Buscar empleados cuyo firstName empiece con una letra específica
    @Query("SELECT e FROM Employee e WHERE LOWER(e.firstName) LIKE LOWER(CONCAT(:letra, '%')) ORDER BY e.firstName ASC")
    List<Employee> findByFirstNameStartingWith(@Param("letra") String letra);
}