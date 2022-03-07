package com.psproj.repository.dao;

import com.psproj.repository.entity.Product;
import com.psproj.repository.entity.ProductCategory;
import com.psproj.repository.entity.User;
import com.psproj.repository.entity.UserRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface ProductCategoryDAO extends JpaRepository<ProductCategory, Long> {

    public List<ProductCategory> findById(@Param("id") Integer id);

    public List<ProductCategory> findByCategoryName(@Param("categoryName") String categoryName);

    @Query(value = "SELECT * FROM product WHERE category_id = ?1 ORDER BY ?1" ,
            countQuery = "SELECT count(*) FROM product WHERE category_id = ?1",
            nativeQuery = true)
    public Page<Product> findByCategory(@Param("categoryId") Long categoryId, Pageable pageable);

}
