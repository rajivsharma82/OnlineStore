package com.psproj.repository.dao;

import com.psproj.repository.entity.Order;
import com.psproj.repository.entity.ProductCategory;
import com.psproj.repository.entity.User;
import com.psproj.repository.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderDao extends JpaRepository<Order, Long> {

    public List<Order> findById(@Param("id") Integer id);

    @Query("select o from Order o where o.user.id = :userId and o.status = :status")
    List<Order> findByUserIdAndStatus(@Param("userId")  Integer userId, @Param("status")  String status);

}

