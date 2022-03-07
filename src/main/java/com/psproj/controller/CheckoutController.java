package com.psproj.controller;

import com.psproj.repository.dao.OrderDao;
import com.psproj.repository.dao.OrderItemDao;
import com.psproj.repository.dao.ProductDAO;
import com.psproj.repository.dao.UserDao;
import com.psproj.repository.entity.Order;
import com.psproj.repository.entity.OrderItem;
import com.psproj.repository.entity.Product;
import com.psproj.repository.entity.User;
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


            //System.out.println("user id by id and status is " + userOrder.get(0).getId());
            if(userOrder != null && userOrder.size() > 0){

                System.out.println("order existing" + userOrder);

                ord = userOrder.get(0);
                totalOrderPrice = userOrder.get(0).getTotalPrice().doubleValue();

                orderIdInSession = ord.getId(); // Get the order id with status pending for the user in session
                 orderItemList = orderItemDao.findByOrderId(orderIdInSession);
            }
            response.addObject("orderIdInSession", orderIdInSession);
            response.addObject("userIdInSession", userIdInSession);
        }

        response.addObject("orderItemListKey", orderItemList);
        response.addObject("totalOrderPrice",totalOrderPrice);




        return response;
    }

}
