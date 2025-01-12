package com.example.demo.service;

import com.example.demo.model.CustomerOrder;
import com.example.demo.model.OrderItem;
import com.example.demo.model.Payment;
import com.example.demo.repository.CustomerOrderRepository;
import com.example.demo.repository.OrderItemRepository;
import com.example.demo.repository.PaymentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional
public class CustomerOrderServiceImpl implements CustomerOrderService {

    private final CustomerOrderRepository customerOrderRepository;
    private final OrderItemRepository orderItemRepository;
    private final PaymentRepository paymentRepository;

    public CustomerOrderServiceImpl(CustomerOrderRepository customerOrderRepository,
                                    OrderItemRepository orderItemRepository,
                                    PaymentRepository paymentRepository) {
        this.customerOrderRepository = customerOrderRepository;
        this.orderItemRepository = orderItemRepository;
        this.paymentRepository = paymentRepository;
    }

    @Override
    public CustomerOrder createCustomerOrder(String customerEmail, String customerAddress, List<OrderItem> items) {
        CustomerOrder order = new CustomerOrder(customerEmail, customerAddress, new java.util.Date(), "Pending");
        for (OrderItem item : items) {
            item.setCustomerOrder(order);
        }
        order.getItems().addAll(items);
        return customerOrderRepository.save(order);
    }

    @Override
    public void cancelCustomerOrder(Long orderId) {
        CustomerOrder order = getCustomerOrderById(orderId);
        order.setDeliveryStatus("Cancelled");
        customerOrderRepository.save(order);
    }

    @Override
    public void addItemToCustomerOrder(Long orderId, OrderItem item) {
        CustomerOrder order = getCustomerOrderById(orderId);
        order.addOrderItem(item);
        customerOrderRepository.save(order);
    }

    @Override
    public void removeItemFromCustomerOrder(Long orderId, Long itemId) {
        CustomerOrder order = getCustomerOrderById(orderId);
        OrderItem item = orderItemRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Item not found with ID: " + itemId));
        order.removeOrderItem(item);
        orderItemRepository.delete(item);
    }

    @Override
    public void processOrderPayment(Long orderId, Payment payment) {
        CustomerOrder order = getCustomerOrderById(orderId);
        payment.setCustomerOrder(order);
        paymentRepository.save(payment);
    }

    @Override
    public void updateOrderDeliveryStatus(Long orderId, String status) {
        CustomerOrder order = getCustomerOrderById(orderId);
        order.setDeliveryStatus(status);
        customerOrderRepository.save(order);
    }

    @Override
    public CustomerOrder getCustomerOrderById(Long id) {
        return customerOrderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("CustomerOrder not found with ID: " + id));
    }

    @Override
    public BigDecimal calculateTotalAmount(Long orderId) {
        CustomerOrder order = customerOrderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        BigDecimal totalAmount = BigDecimal.ZERO;

        for (OrderItem item : order.getItems()) {
            BigDecimal price = BigDecimal.valueOf(item.getProductPrice());
            totalAmount = totalAmount.add(price);
        }

        return totalAmount;
    }
}
