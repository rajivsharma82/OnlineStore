package com.psproj.utilities;

import com.psproj.repository.dao.OrderDao;
import com.psproj.repository.dao.UserDao;
import com.psproj.repository.entity.Order;
import com.psproj.repository.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OnlineStoreUtilities {

    @Autowired
    UserDao userDao;

    @Autowired
    OrderDao orderDao;

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

    public Order pendingUserOrder(){

       User user =  getUserInSession();
       int userIdInSession = user.getId();

        Order ord = new Order();
        List<Order> userOrder = null;
        if(userIdInSession > 0){
            userOrder = orderDao.findByUserIdAndStatus(userIdInSession,"pending");
        }

        // FInd if the order is existing (with pending status for this user) or need to create a new order
        if(userOrder != null && userOrder.size() > 0){
            System.out.println("order existing" + userOrder);
            ord = userOrder.get(0);
        }

        return ord;
    }




}
