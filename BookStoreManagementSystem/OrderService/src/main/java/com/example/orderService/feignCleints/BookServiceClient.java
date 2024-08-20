package com.example.orderService.feignCleints;

import com.example.orderService.dto.Book;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient
public interface BookServiceClient {
    @GetMapping("api/Books/{id}")
    Book getBookById(@PathVariable("id") String id);
}
