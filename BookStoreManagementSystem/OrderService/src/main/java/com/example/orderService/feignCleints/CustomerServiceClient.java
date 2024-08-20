package com.example.orderService.feignCleints;

import com.example.orderService.dto.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("BookService")

public interface CustomerServiceClient {
    @GetMapping("api/customers/{id}")
    Customer getCustomerById(@PathVariable("id") String id);
}
