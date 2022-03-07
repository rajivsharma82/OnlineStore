package com.psproj;

import com.psproj.repository.dao.ProductCategoryDAO;
import com.psproj.repository.dao.ProductDAO;
import com.psproj.repository.dao.UserDao;
import com.psproj.repository.dao.UserRoleDAO;
import com.psproj.repository.entity.Product;
import com.psproj.repository.entity.ProductCategory;
import com.psproj.repository.entity.User;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.math.BigDecimal;
import java.util.List;


@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProductDaoTest {

    @Autowired
    private ProductDAO productDAO;

    @Autowired
    ProductCategoryDAO productCategoryDAO;

    @Test
    @Order(1)
    @Rollback(value = false)
    public void saveProductTest(){
        Product product = new Product();
        ProductCategory productCategory = new ProductCategory();

        productCategory.setCategoryName("test1");
        productCategoryDAO.save(productCategory);

        List<ProductCategory> productCategoryList = productCategoryDAO.findByCategoryName("test1");
        long prodCatId = productCategoryList.get(0).getId();

        product.setSku("TEST-BOOk");
        product.setName("TEST PROD");
        product.setDescription("TEST PROD");
        product.setUnitPrice(BigDecimal.valueOf(100));
        product.setImageUrl("http://www.book.com");
        product.setActive(true);
        product.setUnitsInStock(100);
        product.setCategory(productCategory);
        productDAO.save(product);

//        Assertions.assertTrue(productDAO.findByCategory(prodCatId).size()>0);

    }

}
