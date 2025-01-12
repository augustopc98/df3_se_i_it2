package com.example.demo.repository;

import com.example.demo.model.CustomerOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerOrderRepository extends JpaRepository<CustomerOrder, Long> {
    // Si necesitas lógica adicional, puedes agregar métodos personalizados aquí
}