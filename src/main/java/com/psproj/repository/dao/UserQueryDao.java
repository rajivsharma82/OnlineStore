package com.psproj.repository.dao;

import com.psproj.repository.entity.Order;
import com.psproj.repository.entity.User;
import com.psproj.repository.entity.UserQuery;
import com.psproj.repository.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserQueryDao extends JpaRepository<UserQuery,Integer> {


    @Query("select o from UserQuery o where o.orderTrackingNumber = :OrderTrackingNumber")
    List<UserQuery> findByOrderTrackingNumber(@Param("OrderTrackingNumber")  String OrderTrackingNumber);

    @Query("select o from UserQuery o where o.status = :status")
    List<UserQuery> findByStatusContainingIgnoreCase(@Param("status")  String status);

    @Query("select o from UserQuery o where o.user.id = :userId")
    List<UserQuery> findByUser(@Param("userId")  Integer userId);


}
