package com.psproj.repository.dao;

import com.psproj.repository.entity.Order;
import com.psproj.repository.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderItemDao extends JpaRepository<OrderItem, Long> {

    public List<OrderItem> findById(@Param("id") Integer id);

    @Query("select o from OrderItem o where o.order.id = :orderId and o.productId = :productId")
    List<OrderItem> findByOrderIdAndProductId(@Param("orderId")  Long orderId, @Param("productId")  Long productId);

}

