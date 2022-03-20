package com.psproj.repository.dao;

import com.psproj.repository.entity.User;
import com.psproj.repository.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;

public interface UserDao extends JpaRepository<User,Long> {

    public User findById(@Param("id") Integer id);

    public User findByEmail(@Param("email") String email);

    public List<User> findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCase(@Param("firstName") String firstName, @Param("lastName") String lastName);

    public List<User> findByFirstNameContainingIgnoreCase(@Param("firstName") String firstName);

    @Query("select u from User u where u.firstName = :firstName")
    public List<User> findByFirstName(String firstName);

    @Query("select u from User u where u.username = :username")
    public User findByUsername(@Param("username") String uname);

    @Query(value="SELECT u.* FROM users u WHERE u.firstName like %:firstName%", nativeQuery = true)
    public List<User> findByFirstNameLike(String firstName);

    @Query("select ur from UserRole ur where ur.user.id = :userId")
    List<UserRole> getUserRoles(@Param("userId")  Integer userId);

    @Query(value = "delete from users u where u.email = :email", nativeQuery = true)
    List<UserRole> deleteByEmail(@Param("email")  String email);

    @Query(value="SELECT u.* FROM users u WHERE u.firstName like %:search%  or u.lastName like %:search% ", nativeQuery = true)
    public List<User> findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCase(String search);

}
