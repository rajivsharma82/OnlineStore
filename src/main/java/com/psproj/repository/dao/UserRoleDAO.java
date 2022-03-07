package com.psproj.repository.dao;

import com.psproj.repository.entity.User;
import com.psproj.repository.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;


public interface UserRoleDAO extends JpaRepository<UserRole, Long> {

    @Transactional
    @Modifying
    @Query("delete from UserRole ur where ur.user.id = :userId")
    public void deleteUserRolesByUser(@Param("userId") Integer userId);




}
