package com.example.orderService.service;


import com.example.orderService.dto.Book;
import com.example.orderService.dto.Customer;
import com.example.orderService.entity.Order;
import com.example.orderService.exceptions.InsufficientStockException;
import com.example.orderService.exceptions.InvalidOrderException;
import com.example.orderService.exceptions.OrderNotFoundException;
import com.example.orderService.feignCleints.BookServiceClient;
import com.example.orderService.feignCleints.CustomerServiceClient;
import com.example.orderService.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final BookServiceClient bookServiceClient;
    private final CustomerServiceClient customerServiceClient;

    public OrderService(OrderRepository orderRepository, BookServiceClient bookServiceClient, CustomerServiceClient customerServiceClient) {
        this.orderRepository = orderRepository;
        this.bookServiceClient = bookServiceClient;
        this.customerServiceClient = customerServiceClient;
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(String id) {
        Optional<Order> orderOptional = orderRepository.findById(id);
        if (orderOptional.isPresent()) {
            return orderOptional.get();
        } else {
            throw new OrderNotFoundException("Order with ID " + id + " not found");
        }
    }

    public Order saveOrder(Order order) {
        // Validate quantity

        if (Integer.parseInt(order.getQuantity())<= 0) {
            throw new InvalidOrderException("Quantity must be greater than 0");
        }

        Customer customer = customerServiceClient.getCustomerById(order.getCustId());
        if (customer == null) {
            throw new InvalidOrderException("Invalid customer ID: " + order.getCustId());
        }

        Book book = bookServiceClient.getBookById(order.getBookId());
        if (book == null) {
            throw new InvalidOrderException("Invalid book ID: " + order.getBookId());
        }

        // Assuming book.getStock() returns a String representing the stock quantity
        int availableStock = Integer.parseInt(book.getStock());

// Get the quantity from the order
        int orderQuantity = Integer.parseInt(order.getQuantity());

// Check if there is sufficient stock
        if (availableStock < orderQuantity) {
            throw new InsufficientStockException("Not enough stock for book with ID " + order.getBookId());
        }

        return orderRepository.save(order);
    }

    public void deleteOrder(String id) {
        boolean exists = orderRepository.existsById(id);
        if (!exists) {
            throw new OrderNotFoundException("Order with ID " + id + " not found");
        }
        orderRepository.deleteById(id);
    }
}


