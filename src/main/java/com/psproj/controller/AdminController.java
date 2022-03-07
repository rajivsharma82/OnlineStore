package com.psproj.controller;

import com.psproj.form.EditUserFormBean;
import com.psproj.form.RegisterProductBean;
import com.psproj.repository.dao.ProductCategoryDAO;
import com.psproj.repository.dao.ProductDAO;
import com.psproj.repository.entity.Product;
import com.psproj.repository.entity.ProductCategory;
import com.psproj.repository.entity.User;
import com.psproj.repository.entity.UserRole;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/admin")
// this restricts the controller to admin only, this can be done at the class level or at the method level
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController {

    @Autowired
    ProductDAO productDAO;

    @Autowired
    ProductCategoryDAO productCategoryDAO;

    //@PreAuthorize("hasAuthority('ADMIN', 'USER')")
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public ModelAndView index(HttpServletRequest request, HttpSession session) throws Exception {
        ModelAndView response = new ModelAndView();
        response.setViewName("admin/home");

        log.debug("debug message");
        return response;
    }
//
//    @PreAuthorize("hasAnyAuthority('ADMIN')")
//    @RequestMapping(value = "/manageProduct", method = RequestMethod.GET)
//    public ModelAndView manageProduct( HttpSession session) throws Exception {
//        ModelAndView response = new ModelAndView();
//        response.setViewName("product/manageProduct");
////        System.out.println(productForm);
//        return response;
//    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @RequestMapping(value = "/manageProduct", method = RequestMethod.GET)
    public ModelAndView manageProductDisplay( HttpSession session) throws Exception {
        ModelAndView response = new ModelAndView();
        response.setViewName("product/manageProduct");
        List<Product> productList = productDAO.findAll();
        response.addObject("productList",productList);
//        System.out.println(productForm);
        return response;
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @RequestMapping(value = "/addProduct", method = RequestMethod.GET)
    public ModelAndView addProduct(@Valid RegisterProductBean productForm, BindingResult errors, HttpSession session) throws Exception {
        ModelAndView response = new ModelAndView();
        response.setViewName("product/addproduct");
        System.out.println(productForm);
        return response;
    }


    @RequestMapping(value = "/addProductSubmit", method = RequestMethod.GET)
    public ModelAndView addProductSubmit(@Valid RegisterProductBean productForm,
                                         BindingResult errors,
                                         HttpSession session) throws Exception {
        ModelAndView response = new ModelAndView();
        response.setViewName("product/addproduct");
        response.addObject("ProductFormBeanKey",productForm);

//        System.out.println(productForm);

        // Add Product Category
        // Get the category id from the Category Name
        ProductCategory prodCat = isProdCategoryAvailable(productForm);

        if( prodCat == null ){
            ProductCategory productCategory = new ProductCategory();
            productCategory.setCategoryName(productForm.getProductCategory());
            prodCat = productCategoryDAO.save(productCategory);
        }

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

        return response;
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @RequestMapping(value = "/editProduct", method = RequestMethod.GET)
    public ModelAndView editProduct(@Valid RegisterProductBean productForm,
                                    BindingResult errors,
                                    @RequestParam(required = false) Integer id,
                                    HttpSession session) throws Exception {
        ModelAndView response = new ModelAndView();

        if ( id != null ) {
            // id has been passed to this form so it is an edit
            Product product = productDAO.findById(id.longValue()).get();

            // populate the form bean with the data loaded from the database
            RegisterProductBean form = new RegisterProductBean();

            form.setProductCategory(product.getCategory().getCategoryName());
            form.setSku(product.getSku());
            form.setName(product.getName());
            form.setDescription(product.getDescription());
            form.setImageUrl(product.getImageUrl());
            form.setActive(true);
            form.setUnitPrice(product.getUnitPrice().doubleValue());
            form.setUnitsInStock(product.getUnitsInStock());

            response.addObject("ProductFormBeanKey", form);
            response.addObject("productId", product.getId());
        }

        response.setViewName("product/editproduct");
        System.out.println(productForm);
        return response;
    }

    @RequestMapping(value = "/editProductSubmit", method = RequestMethod.POST)
    public ModelAndView editUserSubmit(@Valid RegisterProductBean productForm,
                                       @RequestParam Integer productId,
                                       BindingResult errors,
                                       HttpSession session) throws Exception {
        ModelAndView response = new ModelAndView();
        //User userEmail = userDao.findByEmail(form.getEmail());

        Product product = productDAO.findById(productId.longValue()).get();

        ProductCategory prodCat = isProdCategoryAvailable(productForm);

        if( prodCat == null ){
            ProductCategory productCategory = new ProductCategory();
            productCategory.setCategoryName(productForm.getProductCategory());
            prodCat = productCategoryDAO.save(productCategory);
        }

        if(errors.hasErrors()) {
            for (FieldError error : errors.getFieldErrors()) {
                productForm.getErrorMessages().add(error.getDefaultMessage());
                System.out.println("error field = " + error.getField() + " message = " + error.getDefaultMessage());
            }
            System.out.println(productForm);
            response.addObject("ProductFormBeanKey",productForm);
//            response.setViewName("product/editproduct");
        }

        else{
            // there are no errors on the form submission

            product.setSku(productForm.getSku());
            product.setName(productForm.getName());
            product.setDescription(productForm.getDescription());
            product.setUnitPrice(BigDecimal.valueOf(productForm.getUnitPrice()));
            product.setImageUrl(productForm.getImageUrl());
            product.setActive(true);
            product.setUnitsInStock(productForm.getUnitsInStock());
            product.setCategory(prodCat);
            productDAO.save(product);
            response.addObject("ProductFormBeanKey",productForm);

        }
        //response.setViewName("product/editproduct");
        response.setViewName("redirect:/admin/manageProduct");
        return response;
    }

    @RequestMapping(value = "/deleteProduct", method = RequestMethod.GET)
    public ModelAndView deleteProduct(@RequestParam Integer id,
                               @RequestParam (required = false) String searchKey ) throws Exception {
        ModelAndView response = new ModelAndView();

        response.setViewName("redirect:/admin/manageProduct");

        Product product = productDAO.findById(id.longValue()).get();
        long deleteProdId = product.getId();
        if ( deleteProdId > 0 ) {
            //userRoleDAO.deleteUserRolesByUser(deleteUserId);
            product.setActive(false);
            productDAO.save(product);
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

}
