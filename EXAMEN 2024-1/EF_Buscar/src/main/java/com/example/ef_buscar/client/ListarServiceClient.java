package com.example.ef_buscar.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "service-listar-hr", url = "http://localhost:8072")
public interface ListarServiceClient {

    @GetMapping("/rh/empleado/orderby/{order}")
    String getEmployeesOrderBy(@PathVariable("order") String order);
}