package com.psproj.repository.dao;

import com.psproj.repository.entity.Product;
import com.psproj.repository.entity.ProductCategory;
import com.psproj.repository.entity.User;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface ProductDAO extends JpaRepository<Product, Long> {

    public List<Product> findByNameContainingIgnoreCase(@Param("name") String name);

//    @Query(value="SELECT u.* FROM product u WHERE u.name like %:name%", nativeQuery = true)
//    public List<Product> findByNameContainingIgnoreCaseLike(@Param("name") String name);

    //@Query(value="SELECT u.* FROM product u WHERE u.category_id = :categoryId", nativeQuery = true)
   // public Page<Product> findByCategory(@Param("categoryId") Long categoryId, Pageable pageable);

    @Query(value = "SELECT * FROM product WHERE category_id = ?1 ORDER BY ?1" ,
            countQuery = "SELECT count(*) FROM product WHERE category_id = ?1",
            nativeQuery = true)
    public Page<Product> findByCategory(@Param("categoryId") Long categoryId, Pageable pageable);

//    @Query(value="SELECT u.* FROM product u WHERE u.category_id = :categoryId", nativeQuery = true)
//    public List<Product> findByCategory(@Param("categoryId") Long categoryId, Pageable pageable);


//    @Query(value = "SELECT * FROM product WHERE category_id = ?1 ORDER BY ?1" ,
//            countQuery = "SELECT count(*) FROM product WHERE category_id = ?1",
//            nativeQuery = true)
    @Query(value="SELECT * FROM product WHERE name like %:name%",
            countQuery = "SELECT count(*) FROM product WHERE name like %:name%",
            nativeQuery = true)
    public Page<Product> findByNameContainingIgnoreCaseLike(@Param("name") String name, Pageable pageable);


}

