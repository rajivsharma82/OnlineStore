package com.psproj.controller;

import com.mysql.cj.x.protobuf.MysqlxDatatypes;
import com.psproj.form.RegisterFormBean;
import com.psproj.form.RegisterProductBean;
import com.psproj.repository.dao.ProductCategoryDAO;
import com.psproj.repository.dao.ProductDAO;
import com.psproj.repository.entity.Order;
import com.psproj.repository.entity.Product;
import com.psproj.repository.entity.ProductCategory;
import com.psproj.repository.entity.User;
import com.psproj.utilities.OnlineStoreUtilities;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


@Controller
public class ProductController {
    //Create Product Controller which will display the Product form to add Product in DB
    @Autowired
    ProductCategoryDAO productCategoryDAO;

    @Autowired
    ProductDAO productDAO;

    @Autowired
    OnlineStoreUtilities onlineStoreUtilities;
//
//    @PreAuthorize("hasAnyAuthority('ADMIN')")
//    @RequestMapping(value = "/addproduct", method = RequestMethod.GET)
//    public ModelAndView addProduct(@Valid RegisterProductBean productForm, BindingResult errors, HttpSession session) throws Exception {
//        ModelAndView response = new ModelAndView();
//        response.setViewName("product/addproduct");
//        System.out.println(productForm);
//        return response;
//    }
//
//
//
//    @RequestMapping(value = "/addProductSubmit", method = RequestMethod.GET)
//    public ModelAndView addProductSubmit(@Valid RegisterProductBean productForm, BindingResult errors, HttpSession session) throws Exception {
//        ModelAndView response = new ModelAndView();
//        response.setViewName("product/addproduct");
//        response.addObject("ProductFormBeanKey",productForm);
//
//        System.out.println(productForm);
//
//
//        // Add Product Category
//        // Get the category id from the Category Name
//        ProductCategory prodCat = isProdCategoryAvailable(productForm);
//
//        if( prodCat == null ){
//            ProductCategory productCategory = new ProductCategory();
//            productCategory.setCategoryName(productForm.getProductCategory());
//            prodCat = productCategoryDAO.save(productCategory);
//        }
//
//       // Add Product
//        Product product = new Product();
//
//        product.setSku(productForm.getSku());
//        product.setName(productForm.getName());
//        product.setDescription(productForm.getDescription());
//        product.setUnitPrice(BigDecimal.valueOf(productForm.getUnitPrice()));
//        product.setImageUrl(productForm.getImageUrl());
//        product.setActive(true);
//        product.setUnitsInStock(productForm.getUnitsInStock());
//        product.setCategory(prodCat);
//        productDAO.save(product);
//
//        return response;
//    }


    @RequestMapping(value = "/productSearch", method = RequestMethod.GET)
    public ModelAndView productSearch(@RequestParam (required = false) String search, HttpSession session) throws Exception {
        ModelAndView response = new ModelAndView();

        response.setViewName("index");

        User user = onlineStoreUtilities.getUserInSession();
        if(user != null) {
            Order order = onlineStoreUtilities.pendingUserOrder();
            if (order != null) {
                session.setAttribute("totalOrderQuantity", order.getTotalQuantity());
                session.setAttribute("totalOrderPrice", order.getTotalPrice());
            }
        }

        if(!StringUtils.isEmpty(search)){
//            List<Product> productSearchList = productDAO.findByNameContainingIgnoreCaseLike(search);
            List<Product> productSearchList = productDAO.findByNameContainingIgnoreCaseLike(search, PageRequest.of(0,4)).getContent();
            response.addObject("productSearchList",productSearchList);
            response.addObject("searchKey", search);

            for(Product product: productSearchList){
                System.out.println(product);
            }

        }else{
            response.setViewName("redirect:/showProducts");
        }

        return response;
    }

    @RequestMapping(value = "/showProducts", method = RequestMethod.GET)
    public ModelAndView showProducts(HttpSession session) throws Exception {
        ModelAndView response = new ModelAndView();
        response.setViewName("redirect:/showProducts/page?currentPage=1");
//        response.setViewName("index");

//        int pageStart = 0;

//        if(StringUtils.isEmpty(next) && StringUtils.isEmpty(previous)) {
//            session.setAttribute("showProductPage", 0);
//            pageStart =0;
//        }
//        else{
//            if(!StringUtils.isEmpty(next)){
//                int val = Integer.parseInt(session.getAttribute("showProductPage").toString());
//                session.setAttribute("showProductPage", val+1);
//                pageStart = val+1;
//
//            }
//            if(!StringUtils.isEmpty(previous)){
//                int val = Integer.parseInt(session.getAttribute("showProductPage").toString());
//                if(val>0){
//                    session.setAttribute("showProductPage", val-1);
//                    pageStart=val-1;
//                }
//            }
//        }

//        System.out.println("page start -------" + pageStart);
//        int currentPage = 1;
//
//            Page<Product> page = productDAO.findAll(PageRequest.of(pageStart,4));
////        List<Product>  productSearchList = productDAO.findAll();
////            List<Product> productSearchList = productPageList.get().collect(Collectors.toList());
//        List<Product> productSearchList = page.getContent();
//        int totalPages = page.getTotalPages();
//        long totalItems = page.getTotalElements();
//
//
//
//
//            response.addObject("productSearchList",productSearchList);
//            response.addObject("pageStart", pageStart);
//            response.addObject("totalItems", totalItems);
//            response.addObject("totalPages", totalPages);
//            response.addObject("currentPage",currentPage);
//
//
//             User user = onlineStoreUtilities.getUserInSession();
//            if(user != null) {
//
//            Order order = onlineStoreUtilities.pendingUserOrder();
//                if (order != null) {
//                session.setAttribute("totalOrderQuantity", order.getTotalQuantity());
//                session.setAttribute("totalOrderPrice", order.getTotalPrice());
//             }
//
//            }

//
//        for(Product product: productSearchList){
//            System.out.println(product);
//            System.out.println("pageStart   " + pageStart);
//        }

        return response;
    }


    @RequestMapping(value = "/showProducts/page", method = RequestMethod.GET)
    public ModelAndView showProductsByPage(@RequestParam (required = false) Integer currentPage,
                                           @RequestParam (required = false) String search,
                                            @RequestParam (required = false) String category,
                                     HttpSession session) throws Exception {
                ModelAndView response = new ModelAndView();
                 response.setViewName("index");

                 int pageNum =0;

                 if(currentPage != null){
                     pageNum = currentPage;
                 }


//        if(StringUtils.isEmpty(next) && StringUtils.isEmpty(previous)) {
//            session.setAttribute("showProductPage", 0);
//            pageStart =0;
//        }
//        else{
//            if(!StringUtils.isEmpty(next)){
//                int val = Integer.parseInt(session.getAttribute("showProductPage").toString());
//                session.setAttribute("showProductPage", val+1);
//                pageStart = val+1;
//
//            }
//            if(!StringUtils.isEmpty(previous)){
//                int val = Integer.parseInt(session.getAttribute("showProductPage").toString());
//                if(val>0){
//                    session.setAttribute("showProductPage", val-1);
//                    pageStart=val-1;
//                }
//            }
//        }
        Page<Product> page = null;
        System.out.println("page start -------" + currentPage);

        if(StringUtils.isEmpty(search) && StringUtils.isEmpty(category)){
            page = productDAO.findAll(PageRequest.of(pageNum-1,4));
        }

        if(StringUtils.isEmpty(search) && ! StringUtils.isEmpty(category)){
            page = getProductsByCat(category, pageNum);
        }

        if(! StringUtils.isEmpty(search) && StringUtils.isEmpty(category)){
            page = getProductsBySearchKeyword(search, pageNum);
        }

//        List<Product>  productSearchList = productDAO.findAll();
//            List<Product> productSearchList = productPageList.get().collect(Collectors.toList());
        List<Product> productSearchList = page.getContent();
        int totalPages = page.getTotalPages();
        long totalItems = page.getTotalElements();

        response.addObject("productSearchList",productSearchList);
        //response.addObject("pageStart", pageStart);
        response.addObject("totalItems", totalItems);
        response.addObject("totalPages", totalPages);
        response.addObject("currentPage",currentPage);
        response.addObject("searchCategory", category);

        response.addObject("searchKey", search);

        User user = onlineStoreUtilities.getUserInSession();
        if(user != null) {

            Order order = onlineStoreUtilities.pendingUserOrder();
            if (order != null) {
                session.setAttribute("totalOrderQuantity", order.getTotalQuantity());
                session.setAttribute("totalOrderPrice", order.getTotalPrice());
            }

        }

        return response;
    }


    public ProductCategory isProdCategoryAvailable(RegisterProductBean productForm){
        ProductCategory productCatFound = null;
        String formProductCategory = productForm.getProductCategory();
        List<ProductCategory> productCategoryList = productCategoryDAO.findByCategoryName(formProductCategory);
        if(productCategoryList != null && productCategoryList.size() > 0) {
             productCatFound = productCategoryList.get(0);
        }
        System.out.println(productCatFound.getCategoryName());
        System.out.println(formProductCategory);
//        return productCatFound.getCategoryName().equals(formProductCategory);
        return productCatFound;
    }


    @RequestMapping(value = "/productCatSearch", method = RequestMethod.GET)
    public ModelAndView productCatSearch(@RequestParam (required = false) String search, HttpSession session) throws Exception {
        long prodCatId = 0;
        ModelAndView response = new ModelAndView();
//        response.setViewName("product/searchProduct");
        response.setViewName("index");

        User user = onlineStoreUtilities.getUserInSession();
        if(user != null) {
            Order order = onlineStoreUtilities.pendingUserOrder();
            if (order != null) {
                session.setAttribute("totalOrderQuantity", order.getTotalQuantity());
                session.setAttribute("totalOrderPrice", order.getTotalPrice());
            }
        }

        if(!StringUtils.isEmpty(search)){
//            List<Product> productSearchList = productDAO.findByNameContainingIgnoreCase(search);
            List<ProductCategory> productCategoryList = productCategoryDAO.findByCategoryName(search);
            if(productCategoryList != null && productCategoryList.size()>0){
                ProductCategory productCategory = productCategoryList.get(0);
                prodCatId = productCategory.getId();
            }
//            List<Product> productSearchList = productDAO.findByNameContainingIgnoreCaseLike(search);
            List<Product> productSearchList = productDAO.findByCategory(prodCatId, PageRequest.of(0,4)).getContent();

            response.addObject("productSearchList",productSearchList);
//            response.addObject("searchKey", search);

            for(Product product: productSearchList){
                System.out.println(product);
            }

        }

        return response;
    }


    private Page<Product> getProductsByCat(String searchCategory, int pageNum) {
        Page<Product> productSearchList = null;
        long prodCatId = 0;
        if (!StringUtils.isEmpty(searchCategory)) {
            List<ProductCategory> productCategoryList = productCategoryDAO.findByCategoryName(searchCategory);
            if (productCategoryList != null && productCategoryList.size() > 0) {
                ProductCategory productCategory = productCategoryList.get(0);
                prodCatId = productCategory.getId();
            }
            productSearchList = productDAO.findByCategory( prodCatId, PageRequest.of(pageNum-1,4));


        }

        return productSearchList;
    }


    private Page<Product> getProductsBySearchKeyword(String search, int pageNum) {
        Page<Product> productSearchList = null;

        if (!StringUtils.isEmpty(search)) {
            productSearchList = productDAO.findByNameContainingIgnoreCaseLike( search, PageRequest.of(pageNum-1,4));
        }

         return productSearchList;
    }
}
