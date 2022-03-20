package com.psproj.repository.dao;

import com.psproj.repository.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderDao extends JpaRepository<Order, Long> {

    @Query("select o from Order o where o.id = :id")
    public List<Order> findById(@Param("id") Integer id);

    @Query("select o from Order o where o.id = :orderId")
    List<Order> findByOrderId(@Param("orderId")  Long orderId);

    @Query("select o from Order o where o.user.id = :userId and o.status = :status")
    List<Order> findByUserIdAndStatus(@Param("userId")  Integer userId, @Param("status")  String status);

    @Query("select o from Order o where o.user.id = :userId")
    List<Order> findByUserId(@Param("userId")  Integer userId);

    @Query("select o from Order o where o.orderTrackingNumber = :OrderTrackingNumber")
    List<Order> findByOrderTrackingNumber(@Param("OrderTrackingNumber")  String OrderTrackingNumber);


}

