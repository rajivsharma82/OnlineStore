package com.psproj.controller;

import com.psproj.form.RegisterProductBean;
import com.psproj.repository.dao.*;
import com.psproj.repository.entity.*;
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

    //Create Product Controller which will display the Product form to add Product in DB

    List<Integer> userCartList = new ArrayList<>();
    Long orderIdInSession;
    int userIdInSession=0;

    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    @RequestMapping(value = "/addToSessionCart", method = RequestMethod.GET)
    public ModelAndView addToSessionCart(@RequestParam (required = false) Integer id, @RequestParam (required = false) String searchKey, HttpServletRequest request, HttpSession session) throws Exception {
        ModelAndView response = new ModelAndView();
        int productId = id;
        // Get user id in session by unique user name
        User userInSession = getUserInSession();
        if(userInSession != null){
            userIdInSession = userInSession.getId();
            System.out.println("userIdInSession " + userIdInSession);
        } else{
//           response.setViewName("redirect:/login/login");
            //response.setViewName("login/login");
            System.out.println("userIdInSession --- default user anonymous  " + userIdInSession);
//            return response;
            //System.out.println("userIdInSession --- default user anonymous" + userIdInSession);
        }

//        String str = request.getReader().lines().collect(Collectors.joining());
//
//        String strArr[] = str.split("=");
//        String prodId = strArr[1];
        System.out.println("product id added to cart" + productId);
        userCartList.add(productId);

        // set the total number of cart List size this would reflect the total number of cart items
//        session.setAttribute("cartInfo", userCartList.size() );
//
//        response.addObject("cartSize", userCartList.size());


        // ordered product details
        // Question - Shall we get the Optional Product List or use the get() ??
        Product  product =  productDAO.findById((long) productId).get();

        // Create the Order table data
        //Step - Check if any pending order exists for the user in session (in Order table).
        Order ord = new Order();
        List<Order> userOrder;
        if(userIdInSession > 0){
            userOrder = orderDao.findByUserIdAndStatus(userIdInSession,"pending");
            //System.out.println("user id by id and status is " + userOrder.get(0).getId());
        } else{
            userOrder = orderDao.findByUserIdAndStatus(30,"pending");
            System.out.println(" taking the hardcoded value of user id 30 ");
        }

        // FInd if the order is existing (with pending status for this user) or need to create a new order
        if(userOrder != null && userOrder.size() > 0){
            System.out.println("order existing" + userOrder);
            ord = userOrder.get(0);
        }else{
            System.out.println("no record found");
            ord.setUser(userDao.findById(userIdInSession));
            ord.setStatus("pending");
            orderDao.save(ord);
            System.out.println(ord.toString());
        }

        // Add the order Item - check if order Item for the same order id and product id already existing
        //if yes then increment the quantity other wise insert a new record with the new product id.
        OrderItem orderItemCart = new OrderItem();
        //List<OrderItem> orderItems = orderItemDao.findByOrderIdAndProductId(ord.getId(), product.getId());
        List<OrderItem> orderItems = orderItemDao.findByOrderIdAndProductId(ord.getId(), product);
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
            orderItemCart.setProduct(product);
            orderItemDao.save(orderItemCart);
            System.out.println("order item inserted " + orderItemCart.toString());
        }

        // update the total quantity  and total price of user's order in the order table
         orderIdInSession = ord.getId();
        Double totalOrderPrice=0d;
        Integer totalOrderQuantity=0;

        List<OrderItem> orderItemList = orderItemDao.findByOrderId(orderIdInSession);
        for(OrderItem orderItem : orderItemList){
            totalOrderPrice += orderItem.getUnitPrice().doubleValue() * orderItem.getQuantity();
            totalOrderQuantity += orderItem.getQuantity();
        }
        System.out.println("total order price " + totalOrderPrice);
        System.out.println("total order quantity " + totalOrderQuantity);

        // save the total price and quantity to order table
        ord.setTotalPrice(BigDecimal.valueOf(totalOrderPrice));
        ord.setTotalQuantity(totalOrderQuantity);
        orderDao.save(ord);

        session.setAttribute("totalOrderQuantity",totalOrderQuantity);
        session.setAttribute("totalOrderPrice",totalOrderPrice);

        System.out.println("total price and total quantity saved in order " + ord.toString());


        // response.addObject("searchKey", searchKey);
        response.setViewName("redirect:/productSearch?search=" + searchKey);
      //response.setViewName("product/searchProduct");
        return response;
    }



    public User getUserInSession(){

        //Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

//        if (principal instanceof UserDetails) {
//            String username = ((UserDetails)principal).getUsername();
//            System.out.println("userdetails if loop " + username);
//        } else {
//            String username = principal.toString();
//            System.out.println("userdetails else loop " + username);
//        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
        System.out.println(" currentUserName value by getName " + currentUserName);
        User user = userDao.findByUsername(currentUserName);
        System.out.println(" user  " + user);
//
//        User currentUserName1 =  (User)authentication.getPrincipal();
//        System.out.println("current user name as principal" + currentUserName1.toString());
//        List<User> userList = userDao.findByFirstName(currentUserName);


        //int userId = userList.get(0).getId();
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


            //System.out.println("user id by id and status is " + userOrder.get(0).getId());
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
//
            session.setAttribute("totalOrderQuantity",totalOrderQuantity);
            session.setAttribute("totalOrderPrice", totalOrderPrice);


        System.out.println("total price and total quantity saved in order " + ord.toString());
//        response.addObject("totalOrderPrice", totalOrderPrice);
//        response.addObject("totalOrderQuantity", totalOrderQuantity);

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
//        System.out.println("total order price " + totalOrderPrice);
//        System.out.println("total order quantity " + totalOrderQuantity);

        // save the total price and quantity to order table
        if(totalOrderPrice > 0 && totalOrderQuantity > 0){
            ord.setTotalPrice(BigDecimal.valueOf(totalOrderPrice));
            ord.setTotalQuantity(totalOrderQuantity);
            orderDao.save(ord);

        }


//
//        System.out.println(cartItemIdVal);
//        System.out.println(cartItemQuantityVal);

        response.setViewName("redirect:/goToCart");

        return response;
    }

}
