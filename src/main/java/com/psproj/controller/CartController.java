package com.psproj.controller;

import com.psproj.form.RegisterProductBean;
import com.psproj.repository.dao.*;
import com.psproj.repository.entity.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class CartController {

    @Autowired
    UserDao userDao;

    @Autowired
    ProductDAO productDAO;

    @Autowired
    OrderDao orderDao;

    @Autowired
    OrderItemDao orderItemDao;

    //Create Product Controller which will display the Product form to add Product in DB

    List<String> userCartList = new ArrayList<>();

    @RequestMapping(value = "/addToSessionCart", method = RequestMethod.POST)
    public ModelAndView addToSessionCart(HttpServletRequest request, HttpSession session) throws Exception {
        ModelAndView response = new ModelAndView();
        String str = request.getReader().lines().collect(Collectors.joining());

        String strArr[] = str.split("=");
        String prodId = strArr[1];
        System.out.println(prodId);
        userCartList.add(prodId);

        // set the total number of cart List size this would reflect the total number of cart items
        session.setAttribute("cartInfo", userCartList.size() );

        int userIdInSession=0;

        // Get user id in session by unique user name
        User userInSession = getUserInSession();
        if(userInSession != null){
            userIdInSession = userInSession.getId();
        }

        // ordered product details
        // Question - Shall we get the Optional Product List or use the get() ??
        Product  product =  productDAO.findById(Long.parseLong(prodId)).get();

        // Create the Order table data
        //Step - Check if any pending order exists for the user in session (in Order table).
        Order ord = new Order();
        List<Order> orderExisting = orderDao.findByUserIdAndStatus(30,"pending");
        if(orderExisting != null && orderExisting.size() > 0){
            System.out.println("order existing" + orderExisting);
            ord = orderExisting.get(0);
        }else{


            System.out.println("no record found");
            ord.setUser(userDao.findById(30));
            ord.setStatus("pending");
            orderDao.save(ord);
            System.out.println(ord.toString());
        }

        // Add the order Item - check if order Item for the same order id and product id already existing
        //if yes then increment the quantity other wise insert a new record with the new product id.
        OrderItem orderItemCart = new OrderItem();
        List<OrderItem> orderItems = orderItemDao.findByOrderIdAndProductId(ord.getId(), product.getId());
        if(orderItems != null && orderItems.size() > 0){
             orderItemCart = orderItems.get(0);
             orderItemCart.setQuantity(orderItemCart.getQuantity()+1);
             orderItemDao.save(orderItemCart);
            System.out.println("updated the quantity of order item cart  " + orderItemCart);
        } else{
            orderItemCart = new OrderItem();
            orderItemCart.setOrder(ord);
            orderItemCart.setQuantity(1);
            orderItemCart.setImageUrl(product.getImageUrl());
            orderItemCart.setUnitPrice(product.getUnitPrice());
            orderItemCart.setProductId(product.getId());
            orderItemDao.save(orderItemCart);
            System.out.println("order item saved" + orderItemCart.toString());
        }

        response.setViewName("redirect:/productSearch");
//        response.setViewName("product/searchProduct");
        return response;
    }

    public User getUserInSession(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
        List<User> userList = userDao.findByFirstName(currentUserName);
        //int userId = userList.get(0).getId();
        if(userList != null && userList.size() > 0){
            return userList.get(0);
        }
        return null;
    }


// Update the cart information - when user click on add to cart servlet is triggering
    // inside controller - create a list (user Cart)
    // when user is calling add ot session cart - add the name | price | image to this list as a string

    @RequestMapping(value = { "/goToCart" }, method = RequestMethod.GET)
    public ModelAndView index(HttpServletRequest request, HttpSession session) throws Exception {
        ModelAndView response = new ModelAndView();
        response.setViewName("cart/cart");

        return response;
    }



}
