package com.psproj.repository.dao;

import com.psproj.repository.entity.Order;
import com.psproj.repository.entity.OrderItem;
import com.psproj.repository.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderItemDao extends JpaRepository<OrderItem, Long> {

    public List<OrderItem> findById(@Param("id") Integer id);

    @Query("select o from OrderItem o where o.order.id = :orderId and o.product = :product")
    List<OrderItem> findByOrderIdAndProductId(@Param("orderId")  Long orderId, @Param("product") Product product);

    @Query("select o from OrderItem o where o.order.id = :orderId")
    List<OrderItem> findByOrderId(@Param("orderId")  Long orderId);

}

