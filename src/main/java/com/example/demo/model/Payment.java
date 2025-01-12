package com.example.demo.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


@Entity
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double amount;

    private String paymentStatus; // Asegúrate de que esta propiedad esté definida

    @Temporal(TemporalType.DATE) // Asegúrate de usar la anotación correcta
    private Date paymentDate;

    @ManyToOne
    private CustomerOrder customerOrder;

    public Payment() {}

    public Payment(Double amount, Date paymentDate, CustomerOrder customerOrder) {
        this.amount = amount;
        this.paymentDate = paymentDate;
        this.customerOrder = customerOrder;
    }

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public CustomerOrder getCustomerOrder() {
        return customerOrder;
    }

    public void setCustomerOrder(CustomerOrder customerOrder) {
        this.customerOrder = customerOrder;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
}