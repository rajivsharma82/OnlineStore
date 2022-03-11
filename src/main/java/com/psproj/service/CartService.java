package com.psproj.service;

import com.psproj.repository.dao.OrderDao;
import com.psproj.repository.dao.OrderItemDao;
import com.psproj.repository.dao.ProductDAO;
import com.psproj.repository.dao.UserDao;
import com.psproj.repository.entity.Order;
import com.psproj.repository.entity.OrderItem;
import com.psproj.repository.entity.Product;
import com.psproj.repository.entity.User;
import com.psproj.utilities.OnlineStoreUtilities;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class CartService {

    @Autowired
    UserDao userDao;

    @Autowired
    ProductDAO productDAO;

    @Autowired
    OrderDao orderDao;

    @Autowired
    OrderItemDao orderItemDao;

    @Autowired
    OnlineStoreUtilities onlineStoreUtilities;

    List<Integer> userCartList = new ArrayList<>();

    int userIdInSession = 0;

    /*
        input product id
        Get logged in User
        Add the  product id to the userCartList(arraylist)
     */

    public boolean updateCartAndDB(int id, HttpSession session) {
        int productId = id;
        // Get user id in session by unique user name
        User userInSession = onlineStoreUtilities.getUserInSession();

        if (userInSession != null) {
            userIdInSession = userInSession.getId();
            log.info("userIdInSession " + userIdInSession);
        } else {
            log.info("userIdInSession --- default user anonymous  " + userIdInSession);

            return false;
        }

        //product id added to cart
        userCartList.add(productId);
        Product product = productDAO.findById((long) productId).get();
        List<Order> userOrder;

        if (userIdInSession > 0) {
            userOrder = orderDao.findByUserIdAndStatus(userIdInSession, "pending");
        } else {
            return false;
        }

        Order ord = fetchOrCreatePendingOrder(userOrder, userDao);
        log.info(ord.getId().toString());
        incExistingOrCreateNewOrderItem(orderItemDao, ord, product);

        // update the total quantity  and total price of user's order in the order table
        updateTotalQAndPriceOnOrder(orderItemDao, ord, orderDao , session);

        return true;
    }

    private void updateTotalQAndPriceOnOrder(OrderItemDao orderItemDao, Order ord, OrderDao orderDao, HttpSession session) {
        Double totalOrderPrice = 0d;
        Integer totalOrderQuantity = 0;

        List<OrderItem> orderItemList = orderItemDao.findByOrderId(ord.getId());
        for (OrderItem orderItem : orderItemList) {
            totalOrderPrice += orderItem.getUnitPrice().doubleValue() * orderItem.getQuantity();
            totalOrderQuantity += orderItem.getQuantity();
        }
        log.info("total order price " + totalOrderPrice);
        log.info("total order quantity " + totalOrderQuantity);

        // save the total price and quantity to order table
        ord.setTotalPrice(BigDecimal.valueOf(totalOrderPrice));
        ord.setTotalQuantity(totalOrderQuantity);
        orderDao.save(ord);

        session.setAttribute("totalOrderQuantity", totalOrderQuantity);
        session.setAttribute("totalOrderPrice", totalOrderPrice);

        System.out.println("total price and total quantity saved in order " + ord.toString());
    }

    private void incExistingOrCreateNewOrderItem(OrderItemDao orderItemDao, Order ord, Product product) {
                OrderItem orderItemCart = new OrderItem();

        List<OrderItem> orderItems = orderItemDao.findByOrderIdAndProductId(ord.getId(), product);
        if (orderItems != null && orderItems.size() > 0) {
            orderItemCart = orderItems.get(0);
            orderItemCart.setQuantity(orderItemCart.getQuantity() + 1);
            orderItemDao.save(orderItemCart);
            System.out.println("updated the quantity of order item cart  " + orderItemCart);
        } else {
            orderItemCart = new OrderItem();
            orderItemCart.setOrder(ord);
            orderItemCart.setQuantity(1);
            orderItemCart.setImageUrl(product.getImageUrl());
            orderItemCart.setUnitPrice(product.getUnitPrice());
            orderItemCart.setProduct(product);
            orderItemDao.save(orderItemCart);
            System.out.println("order item inserted " + orderItemCart.toString());
        }
    }

    private Order fetchOrCreatePendingOrder(List<Order> userOrder, UserDao userDao) {
        Order ord = new Order();
        if (userOrder != null && userOrder.size() > 0) {
            log.info("order existing" + userOrder);
            ord = userOrder.get(0);
        } else {
            System.out.println("no record found");
            String orderTrackingNumber = orderTrackingNumber();
            ord.setUser(userDao.findById(userIdInSession));
            ord.setStatus("pending");
            ord.setOrderTrackingNumber(orderTrackingNumber);
            orderDao.save(ord);
        }
        return ord;
    }

    public String orderTrackingNumber() {
        return UUID.randomUUID().toString();
    }

}
