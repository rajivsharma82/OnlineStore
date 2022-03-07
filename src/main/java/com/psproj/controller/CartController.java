package com.psproj.controller;

import com.psproj.form.RegisterProductBean;
import com.psproj.repository.dao.*;
import com.psproj.repository.entity.*;
import com.psproj.service.CartService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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

    @Autowired
    CartService cartService;

    //Create Product Controller which will display the Product form to add Product in DB

    List<Integer> userCartList = new ArrayList<>();
    Long orderIdInSession;
    int userIdInSession=0;

    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    @RequestMapping(value = "/addToSessionCart", method = RequestMethod.GET)
    public ModelAndView addToSessionCart(@RequestParam (required = false) Integer id, @RequestParam (required = false) String searchKey, HttpServletRequest request, HttpSession session) throws Exception {
        ModelAndView response = new ModelAndView();

        boolean cartAddResult = cartService.updateCartAndDB(id, session);

        if(!cartAddResult){
            response.setViewName("redirect:error/error");
        }

        if(!StringUtils.isEmpty(searchKey)){
            response.setViewName("redirect:/productSearch?search=" + searchKey);
        } else{
            response.setViewName("redirect:/showProducts");
        }

        return response;
    }

    public User getUserInSession(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
        System.out.println(" currentUserName value by getName " + currentUserName);
        User user = userDao.findByUsername(currentUserName);
        System.out.println(" user  " + user);


        if(user != null){
            return user;
        }
        return null;
    }

    // Update the cart information - when user click on add to cart servlet is triggering
    // inside controller - create a list (user Cart)
    // when user is calling add ot session cart - add the name | price | image to this list as a string

    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    @RequestMapping(value = { "/goToCart" }, method = RequestMethod.GET)
    public ModelAndView index(HttpServletRequest request, HttpSession session) throws Exception {
        List<OrderItem> orderItemList = new ArrayList<>();
        Double totalOrderPrice=0d;

        ModelAndView response = new ModelAndView();
        response.setViewName("cart/cart");

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

                System.out.println("order existing" + userOrder);

                ord = userOrder.get(0);
                totalOrderPrice = userOrder.get(0).getTotalPrice().doubleValue();

                orderIdInSession = ord.getId(); // Get the order id with status pending for the user in session
                 orderItemList = orderItemDao.findByOrderId(orderIdInSession);
            }

        }

        response.addObject("orderItemListKey", orderItemList);
        response.addObject("orderIdInSession", orderIdInSession);
        response.addObject("totalOrderPrice",totalOrderPrice);

        return response;
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    @RequestMapping(value = "/deleteItemFromCart", method = RequestMethod.GET)
    public ModelAndView deleteFromCart(@RequestParam (required = false) Integer id, @RequestParam (required = false) Long orderId, HttpServletRequest request, HttpSession session) throws Exception {
        ModelAndView response = new ModelAndView();
        int cartItemId = id;
        Long cartItemOrderId = orderId;

        orderItemDao.deleteById(id.longValue());

        Order ord = orderDao.findById(orderId).get();


        Double totalOrderPrice=0d;
        Integer totalOrderQuantity=0;

        List<OrderItem> orderItemList = orderItemDao.findByOrderId((long) cartItemOrderId);
        for(OrderItem orderItem : orderItemList){
            totalOrderPrice += orderItem.getUnitPrice().doubleValue() * orderItem.getQuantity();
            totalOrderQuantity += orderItem.getQuantity();
        }
        System.out.println("total order price " + totalOrderPrice);
        System.out.println("total order quantity " + totalOrderQuantity);

        // save the total price and quantity to order table
        if(totalOrderPrice > 0 && totalOrderQuantity > 0){
            ord.setTotalPrice(BigDecimal.valueOf(totalOrderPrice));
            ord.setTotalQuantity(totalOrderQuantity);
            orderDao.save(ord);

        } else{
            orderDao.deleteById(cartItemOrderId);
        }

            session.setAttribute("totalOrderQuantity",totalOrderQuantity);
            session.setAttribute("totalOrderPrice", totalOrderPrice);

        // total price and total quantity saved in order

        response.setViewName("redirect:/goToCart");

        return response;
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    @RequestMapping(value = "/updateItemFromCart", method = RequestMethod.GET)
    public ModelAndView updateItemFromCart(@RequestParam (required = true) String cartItemId,
                                           @RequestParam (required = true) String quantityField,
                                           @RequestParam (required = true) String cartItemOrderId,
                                           HttpServletRequest request, HttpSession session) throws Exception {
        ModelAndView response = new ModelAndView();
        Long cartItemIdVal = Long.parseLong(cartItemId);
        int cartItemQuantityVal = Integer.parseInt(quantityField);
        Long cartItemOrderIdVal = Long.parseLong(cartItemOrderId);


        OrderItem orderItemDaoRec = orderItemDao.findById(cartItemIdVal.longValue()).get();
        orderItemDaoRec.setQuantity(cartItemQuantityVal);
        orderItemDao.save(orderItemDaoRec);


        Order ord = orderDao.findById(cartItemOrderIdVal).get();
        Double totalOrderPrice=0d;
        Integer totalOrderQuantity=0;

        List<OrderItem> orderItemList = orderItemDao.findByOrderId(cartItemOrderIdVal);
        for(OrderItem orderItem : orderItemList){
            totalOrderPrice += orderItem.getUnitPrice().doubleValue() * orderItem.getQuantity();
            totalOrderQuantity += orderItem.getQuantity();
        }


        // save the total price and quantity to order table
        if(totalOrderPrice > 0 && totalOrderQuantity > 0){
            ord.setTotalPrice(BigDecimal.valueOf(totalOrderPrice));
            ord.setTotalQuantity(totalOrderQuantity);
            orderDao.save(ord);
        }

        response.setViewName("redirect:/goToCart");
        return response;
    }

}
