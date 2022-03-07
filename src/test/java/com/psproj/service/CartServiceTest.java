package com.psproj.service;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import com.psproj.repository.dao.OrderDao;
import com.psproj.repository.dao.OrderItemDao;
import com.psproj.repository.dao.ProductDAO;
import com.psproj.repository.dao.UserDao;
import com.psproj.repository.entity.*;
import com.psproj.utilities.OnlineStoreUtilities;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)

public class CartServiceTest {

    @Mock
    UserDao userDao;

    @Mock
    ProductDAO productDAO;

    @Mock
    OrderDao orderDao;

    @Mock
    OrderItemDao orderItemDao;

    @Mock
    OnlineStoreUtilities onlineStoreUtilities;

    @InjectMocks
    CartService cartService;

    @Spy
    HttpSession session;
    @Test
    public void userNotInSessionFailure(){

        when(onlineStoreUtilities.getUserInSession()).thenReturn(null);
        boolean returnVal = cartService.updateCartAndDB(10, session);
        Assertions.assertFalse(returnVal);
    }

    @Test
    public void createNewOrderAndOrderItem(){
        User user = new User();
        user.setId(1);
        user.setEmail("test2@gmail.com");
        user.setFirstName("Test2 First Name");
        user.setLastName("Test2 Last Name");
        user.setPhone("111-111-1111");
        user.setUsername("testuser2");

        Product product = new Product();
        product.setSku("1");
        product.setName("BOOK1");
        product.setDescription("BOOk");
        product.setUnitPrice(BigDecimal.valueOf(25.0));
        product.setImageUrl("URL");
        product.setActive(true);
        product.setUnitsInStock(100);
        product.setCategory(new ProductCategory());

        Order order = new Order();
        order.setStatus("pending");
        order.setId(2L);
        order.setOrderTrackingNumber("asa");
        order.setTotalQuantity(100);
        order.setTotalPrice(BigDecimal.valueOf(500));
        order.setTotalQuantity(100);


        List<Order> orderList = new ArrayList<>();
        orderList.add(order);

        when(onlineStoreUtilities.getUserInSession()).thenReturn(user);
        when(productDAO.findById(10L)).thenReturn(Optional.of(product));
        when(orderItemDao.save(any())).thenReturn(new OrderItem());
        when(orderDao.save(any())).thenReturn( order);
        when(orderDao.findByUserIdAndStatus(1,"pending")).thenReturn(orderList);

        boolean returnVal = cartService.updateCartAndDB(10, session);
        Assertions.assertTrue(returnVal);

    }

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

}
