package com.example.orderService.entity;


import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    private String Id;

    @NotEmpty(message = "Customer ID cannot be empty")
    private String custId;

    @NotEmpty(message = "Book ID cannot be empty")
    private String bookId;

    @NotEmpty(message = "quantity cannot be nul")
    @Min(value=1, message = "quantity must be at least 1")
    private String quantity;

    @NotEmpty(message = "status cannot be empty")
    @Pattern(regexp = "PENDING|CONFIRMED|CANCELLED", message = "invalid status")
    private String status;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public @NotEmpty(message = "Customer ID cannot be empty") String getCustId() {
        return custId;
    }

    public void setCustId(@NotEmpty(message = "Customer ID cannot be empty") String custId) {
        this.custId = custId;
    }

    public @NotEmpty(message = "Book ID cannot be empty") String getBookId() {
        return bookId;
    }

    public void setBookId(@NotEmpty(message = "Book ID cannot be empty") String bookId) {
        this.bookId = bookId;
    }

    public @NotEmpty(message = "quantity cannot be nul") @Min(value = 1, message = "quantity must be at least 1") String getQuantity() {
        return quantity;
    }

    public void setQuantity(@NotEmpty(message = "quantity cannot be nul") @Min(value = 1, message = "quantity must be at least 1") String quantity) {
        this.quantity = quantity;
    }

    public @NotEmpty(message = "status cannot be empty") @Pattern(regexp = "PENDING|CONFIRMED|CANCELLED", message = "invalid status") String getStatus() {
        return status;
    }

    public void setStatus(@NotEmpty(message = "status cannot be empty") @Pattern(regexp = "PENDING|CONFIRMED|CANCELLED", message = "invalid status") String status) {
        this.status = status;
    }

    public Order(String id, String custId, String bookId, String quantity, String status) {
        Id = id;
        this.custId = custId;
        this.bookId = bookId;
        this.quantity = quantity;
        this.status = status;
    }
}
