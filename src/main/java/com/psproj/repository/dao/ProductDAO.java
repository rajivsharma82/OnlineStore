package com.psproj.repository.dao;

import com.psproj.repository.entity.Product;
import com.psproj.repository.entity.ProductCategory;
import com.psproj.repository.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface ProductDAO extends JpaRepository<Product, Long> {

    public List<Product> findByNameContainingIgnoreCase(@Param("name") String name);

    @Query(value="SELECT u.* FROM product u WHERE u.name like %:name%", nativeQuery = true)
    public List<Product> findByNameContainingIgnoreCaseLike(@Param("name") String name);

}
