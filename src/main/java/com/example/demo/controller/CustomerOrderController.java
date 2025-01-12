package com.example.demo.controller;

import com.example.demo.model.CustomerOrder;
import com.example.demo.model.OrderItem;
import com.example.demo.model.Payment;
import com.example.demo.service.CustomerOrderService;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/customer-orders")
public class CustomerOrderController {

    private final CustomerOrderService customerOrderService;

    public CustomerOrderController(CustomerOrderService customerOrderService) {
        this.customerOrderService = customerOrderService;
    }

    @PostMapping
    public CustomerOrder createCustomerOrder(@RequestBody CustomerOrder order) {
        return customerOrderService.createCustomerOrder(
                order.getCustomerEmail(),
                order.getCustomerAddress(),
                order.getItems()
        );
    }

    @GetMapping("/{id}")
    public CustomerOrder getCustomerOrderById(@PathVariable Long id) {
        return customerOrderService.getCustomerOrderById(id);
    }

    @PostMapping("/{id}/items")
    public void addItemToCustomerOrder(@PathVariable Long id, @RequestBody OrderItem item) {
        customerOrderService.addItemToCustomerOrder(id, item);
    }

    @DeleteMapping("/{id}/items/{itemId}")
    public void removeItemFromCustomerOrder(@PathVariable Long id, @PathVariable Long itemId) {
        customerOrderService.removeItemFromCustomerOrder(id, itemId);
    }

    @PostMapping("/{id}/payments")
    public void processOrderPayment(@PathVariable Long id, @RequestBody Payment payment) {
        customerOrderService.processOrderPayment(id, payment);
    }

    @PutMapping("/{id}/status")
    public void updateOrderDeliveryStatus(@PathVariable Long id, @RequestParam String status) {
        customerOrderService.updateOrderDeliveryStatus(id, status);
    }

    @GetMapping("/{id}/total")
    public BigDecimal calculateTotalAmount(@PathVariable Long id) {
        return customerOrderService.calculateTotalAmount(id);
    }

    @DeleteMapping("/{id}")
    public void cancelCustomerOrder(@PathVariable Long id) {
        customerOrderService.cancelCustomerOrder(id);
    }
}
