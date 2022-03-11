package com.psproj.controller;

import com.psproj.repository.dao.*;
import com.psproj.repository.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CheckoutController {

    @Autowired
    UserDao userDao;

    @Autowired
    ProductDAO productDAO;

    @Autowired
    OrderDao orderDao;

    @Autowired
    OrderItemDao orderItemDao;

    @Autowired
    UserBillingDAO userBillingDAO;

    //Create Product Controller which will display the Product form to add Product in DB

    List<Integer> userCartList = new ArrayList<>();
    Long orderIdInSession;
    int userIdInSession=0;


    public User getUserInSession(){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
        System.out.println(" currentUserName value by getName " + currentUserName);
        User user = userDao.findByUsername(currentUserName);
        System.out.println(" user  " + user);
//

        if(user != null){
            return user;
        }
        return null;
    }


    // Update the cart information - when user click on add to cart servlet is triggering
    // inside controller - create a list (user Cart)
    // when user is calling add to session cart - add the name | price | image to this list as a string

    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    @RequestMapping(value = { "/checkOutCart" }, method = RequestMethod.GET)
    public ModelAndView checkout(HttpServletRequest request, HttpSession session) throws Exception {
        List<OrderItem> orderItemList = new ArrayList<>();
        Double totalOrderPrice=0d;
        UserBilling userBilling = null;

        ModelAndView response = new ModelAndView();
        response.setViewName("cart/checkout");

        //Find the user Id in session
        User userInSession = getUserInSession();

        if(userInSession != null){
            userIdInSession = userInSession.getId();
            System.out.println("userIdInSession " + userIdInSession);
        } else{
            System.out.println("userIdInSession --- default user anonymous  " + userIdInSession);
        }

        // Get the Order Id with pending status for the user in session
        Order ord = new Order();
        List<Order> userOrder;
        if(userIdInSession > 0){
            userOrder = orderDao.findByUserIdAndStatus(userIdInSession,"pending");

            if(userOrder != null && userOrder.size() > 0){
                 ord = userOrder.get(0);
                 totalOrderPrice = userOrder.get(0).getTotalPrice().doubleValue();
                 orderIdInSession = ord.getId(); // Get the order id with status pending for the user in session
                 orderItemList = orderItemDao.findByOrderId(orderIdInSession);
            }
            response.addObject("orderIdInSession", orderIdInSession);
            response.addObject("userIdInSession", userIdInSession);

           List<UserBilling> userBillingList = userBillingDAO.findByUserId(userIdInSession);
           if(userBillingList != null && userBillingList.size()>0){
               userBilling = userBillingList.get(0);
           }
           response.addObject("userBilling", userBilling);
        }
        response.addObject("orderItemListKey", orderItemList);
        response.addObject("totalOrderPrice",totalOrderPrice);
        return response;
    }

}
