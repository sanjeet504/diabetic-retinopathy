package com.example.orderService.controller;


import com.example.orderService.entity.Order;
import com.example.orderService.exceptions.InsufficientStockException;
import com.example.orderService.exceptions.InvalidOrderException;
import com.example.orderService.exceptions.OrderNotFoundException;
import com.example.orderService.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getOrderById(@PathVariable String id) {
        try {
            Order order = orderService.getOrderById(id);
            Map<String, Object> response = new HashMap<>();
            response.put("order", order);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (OrderNotFoundException e) {
            Map<String, Object> response = new HashMap<>();
            response.put("message", e.getMessage());
            response.put("status", HttpStatus.NOT_FOUND.value());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> createOrder(@Valid @RequestBody Order order) {
        try {
            Order savedOrder = orderService.saveOrder(order);
            Map<String, Object> response = new HashMap<>();
            response.put("order", savedOrder);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (InvalidOrderException | InsufficientStockException e) {
            Map<String, Object> response = new HashMap<>();
            response.put("message", e.getMessage());
            response.put("status", HttpStatus.BAD_REQUEST.value());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateOrder(@PathVariable String id, @Valid @RequestBody Order order) {
        try {
            Order existingOrder = orderService.getOrderById(id);
            existingOrder.setBookId(order.getBookId());
            existingOrder.setStatus(order.getStatus());
            existingOrder.setQuantity(order.getQuantity());
            Order updatedOrder = orderService.saveOrder(existingOrder);
            Map<String, Object> response = new HashMap<>();
            response.put("order", updatedOrder);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (OrderNotFoundException e) {
            Map<String, Object> response = new HashMap<>();
            response.put("message", e.getMessage());
            response.put("status", HttpStatus.NOT_FOUND.value());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        } catch (InvalidOrderException | InsufficientStockException e) {
            Map<String, Object> response = new HashMap<>();
            response.put("message", e.getMessage());
            response.put("status", HttpStatus.BAD_REQUEST.value());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteOrder(@PathVariable String id) {
        try {
            orderService.deleteOrder(id);
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Order deleted successfully");
            response.put("status", HttpStatus.NO_CONTENT.value());
            return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
        } catch (OrderNotFoundException e) {
            Map<String, Object> response = new HashMap<>();
            response.put("message", e.getMessage());
            response.put("status", HttpStatus.NOT_FOUND.value());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }
}
