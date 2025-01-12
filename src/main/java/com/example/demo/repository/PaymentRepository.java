package com.example.demo.repository;

import com.example.demo.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    @Query("SELECT p FROM Payment p WHERE p.customerOrder.id = :orderId")
    List<Payment> findPaymentsByCustomerOrderId(@Param("orderId") Long orderId);

    @Query("SELECT SUM(p.amount) FROM Payment p WHERE p.customerOrder.id = :orderId")
    BigDecimal calculateTotalPaidByCustomerOrderId(@Param("orderId") Long orderId);



    @Query("SELECT p FROM Payment p WHERE p.paymentDate BETWEEN :startDate AND :endDate")
    List<Payment> findPaymentsBetweenDates(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

    List<Payment> findByPaymentStatus(String paymentStatus);
}
