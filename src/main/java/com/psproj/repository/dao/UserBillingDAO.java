package com.psproj.repository.dao;

import com.psproj.repository.entity.User;
import com.psproj.repository.entity.UserBilling;
import com.psproj.repository.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.criteria.CriteriaBuilder;
import javax.transaction.Transactional;
import java.util.List;


public interface UserBillingDAO extends JpaRepository<UserBilling, Integer> {

    @Query("select ub from UserBilling ub where ub.user.id = :userId")
    public List<UserBilling> findByUserId(@Param("userId") Integer userId);

}
