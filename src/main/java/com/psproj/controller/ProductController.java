package com.psproj.controller;

import com.psproj.form.RegisterFormBean;
import com.psproj.form.RegisterProductBean;
import com.psproj.repository.dao.ProductCategoryDAO;
import com.psproj.repository.dao.ProductDAO;
import com.psproj.repository.entity.Product;
import com.psproj.repository.entity.ProductCategory;
import com.psproj.repository.entity.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Controller
public class ProductController {

    //Create Product Controller which will display the Product form to add Product in DB

    @Autowired
    ProductCategoryDAO productCategoryDAO;

    @Autowired
    ProductDAO productDAO;



    @RequestMapping(value = "/addproduct", method = RequestMethod.GET)
    public ModelAndView addProduct(@Valid RegisterProductBean productForm, BindingResult errors, HttpSession session) throws Exception {
        ModelAndView response = new ModelAndView();
        response.setViewName("product/addproduct");
        System.out.println(productForm);
        return response;
    }

    @RequestMapping(value = "/addProductSubmit", method = RequestMethod.GET)
    public ModelAndView addProductSubmit(@Valid RegisterProductBean productForm, BindingResult errors, HttpSession session) throws Exception {
        ModelAndView response = new ModelAndView();
        response.setViewName("product/addproduct");
        response.addObject("ProductFormBeanKey",productForm);

        System.out.println(productForm);


//      Add Product Category
//        ProductCategory productCategory = new ProductCategory();
//        productCategory.setCategoryName(productForm.getProductCategory());
//        productCategoryDAO.save(productCategory);

        // Get the category id from the Category Name

        ProductCategory prodCat = isProdCategoryAvailable(productForm);

        if( prodCat == null ){
            ProductCategory productCategory = new ProductCategory();
            productCategory.setCategoryName(productForm.getProductCategory());
            prodCat = productCategoryDAO.save(productCategory);

        }

//        String formProductCategory = productForm.getProductCategory();
//        List<ProductCategory> productCategoryList = productCategoryDAO.findByCategoryName(formProductCategory);
//        ProductCategory productCatFound = productCategoryList.get(0);
//        System.out.println(productCatFound.getCategoryName());
//        System.out.println(formProductCategory);
//        System.out.println(productCatFound.getCategoryName().equals(formProductCategory));

       // Add Product
        Product product = new Product();

        product.setSku(productForm.getSku());
        product.setName(productForm.getName());
        product.setDescription(productForm.getDescription());
        product.setUnitPrice(BigDecimal.valueOf(productForm.getUnitPrice()));
        product.setImageUrl(productForm.getImageUrl());
        product.setActive(true);
        product.setUnitsInStock(productForm.getUnitsInStock());
        product.setCategory(prodCat);
        productDAO.save(product);
        //List<ProductCategory> productCategories = productCategoryDAO.findAll();
//        List<Product> productList = productDAO.findAll();

//        System.out.println("set of products are " + productList);

        return response;
    }


    @RequestMapping(value = "/productSearch", method = RequestMethod.GET)
    public ModelAndView productSearch(@RequestParam (required = true) String search, HttpSession session) throws Exception {
        ModelAndView response = new ModelAndView();
        response.setViewName("product/searchProduct");

        if(!StringUtils.isEmpty(search)){
            List<Product> productSearchList = productDAO.findByNameContainingIgnoreCase(search);

            response.addObject("productSearchList",productSearchList);
            response.addObject("searchKey", search);

            for(Product product: productSearchList){
                System.out.println(product);
            }

        }
//
//        response.addObject("ProductFormBeanKey",productForm);
//
//        System.out.println(productForm);



        return response;
    }


    public ProductCategory isProdCategoryAvailable(RegisterProductBean productForm){
        String formProductCategory = productForm.getProductCategory();
        List<ProductCategory> productCategoryList = productCategoryDAO.findByCategoryName(formProductCategory);
        ProductCategory productCatFound = productCategoryList.get(0);
//        System.out.println(productCatFound.getCategoryName());
//        System.out.println(formProductCategory);
        //return productCatFound.getCategoryName().equals(formProductCategory);
        return productCatFound;
    }

}
