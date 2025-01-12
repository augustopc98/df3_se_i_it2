package com.example.demo.repository;

import com.example.demo.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    @Query("SELECT o FROM OrderItem o WHERE o.customerOrder.id = :orderId")
    List<OrderItem> findItemsByCustomerOrderId(@Param("orderId") Long orderId);

    @Query("SELECT SUM(o.quantity) FROM OrderItem o WHERE o.productId = :productId")
    Integer calculateTotalQuantityByProductId(@Param("productId") Long productId);

    List<OrderItem> findByProductPriceGreaterThan(BigDecimal price);
}
