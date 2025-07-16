package com.example.ef_buscar_20242;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "service-employee-hr", url = "http://localhost:8072")
public interface ServiceBuscarClient {

    @GetMapping("/rh/empleado/orderby/{ordercolum}")
    String listar(@PathVariable("ordercolum") String ordercolum);

}