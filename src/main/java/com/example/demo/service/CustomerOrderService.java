package com.example.demo.service;

import com.example.demo.model.CustomerOrder;
import com.example.demo.model.OrderItem;
import com.example.demo.model.Payment;

import java.math.BigDecimal;
import java.util.List;

public interface CustomerOrderService {
    CustomerOrder createCustomerOrder(String customerEmail, String customerAddress, List<OrderItem> items);
    void cancelCustomerOrder(Long orderId);
    void addItemToCustomerOrder(Long orderId, OrderItem item);
    void removeItemFromCustomerOrder(Long orderId, Long itemId);
    void processOrderPayment(Long orderId, Payment payment);
    void updateOrderDeliveryStatus(Long orderId, String status);
    CustomerOrder getCustomerOrderById(Long id);
    BigDecimal calculateTotalAmount(Long orderId);
}
