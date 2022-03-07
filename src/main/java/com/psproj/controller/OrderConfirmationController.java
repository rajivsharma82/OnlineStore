package com.psproj.controller;

import com.psproj.form.UserBillingBean;
import com.psproj.repository.dao.OrderDao;
import com.psproj.repository.dao.OrderItemDao;
import com.psproj.repository.dao.UserBillingDAO;
import com.psproj.repository.dao.UserDao;
import com.psproj.repository.entity.Order;
import com.psproj.repository.entity.OrderItem;
import com.psproj.repository.entity.User;
import com.psproj.repository.entity.UserBilling;
import com.psproj.utilities.OnlineStoreUtilities;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.util.List;

@Controller
public class OrderConfirmationController {

	@Autowired
	UserBillingDAO userBillingDAO;

	@Autowired
	UserDao userDao;

	@Autowired
	OrderDao orderDao;

	@Autowired
	OrderItemDao orderItemDao;

	@Autowired
	OnlineStoreUtilities onlineStoreUtilities;

	String orderTrackingNumber;

	public final String orderComplete = "complete";

	@RequestMapping(value =  "/orderconfirmation" , method = RequestMethod.POST)
	public ModelAndView index(@Valid UserBillingBean form,
							  @RequestParam Integer userIdInSession,
							  @RequestParam Integer orderIdInSession,
							  BindingResult errors,
							  HttpSession session) throws Exception {
		ModelAndView response = new ModelAndView();



		System.out.println("user id in session " + userIdInSession);
		System.out.println("order id in session " + orderIdInSession);
		System.out.println(form.getAddress());
		System.out.println(form.getFullname());

		List<UserBilling> userBillingList =  userBillingDAO.findByUserId(userIdInSession);
		User user = userDao.findById(userIdInSession);

		if(userBillingList != null && userBillingList.size() >0 && user != null){
			UserBilling userBillObj = userBillingList.get(0);

			userBillObj.setFullname(form.getFullname());
			userBillObj.setEmail(form.getEmail());
			userBillObj.setAddress(form.getAddress());
			userBillObj.setCity(form.getCity());
			userBillObj.setState(form.getState());
			userBillObj.setZip(form.getZip());
			userBillObj.setCardname(form.getCardname());
			userBillObj.setCardnumber(form.getCardnumber());
			userBillObj.setExpyear(form.getExpyear());
			userBillObj.setExpmonth(form.getExpmonth());
			userBillObj.setCvv(form.getCvv());
			userBillObj.setUser(user);

			userBillingDAO.save(userBillObj);

		} else if(user != null) {
			UserBilling userBillObj = new UserBilling();

			userBillObj.setFullname(form.getFullname());
			userBillObj.setEmail(form.getEmail());
			userBillObj.setAddress(form.getAddress());
			userBillObj.setCity(form.getCity());
			userBillObj.setState(form.getState());
			userBillObj.setZip(form.getZip());
			userBillObj.setCardname(form.getCardname());
			userBillObj.setCardnumber(form.getCardnumber());
			userBillObj.setExpyear(form.getExpyear());
			userBillObj.setExpmonth(form.getExpmonth());
			userBillObj.setCvv(form.getCvv());
			userBillObj.setUser(user);
			userBillingDAO.save(userBillObj);

		}

		List<Order> orders = orderDao.findByUserIdAndStatus(userIdInSession,"pending");
		if(orders != null && orders.size() > 0){
			Order order = orders.get(0);
			orderTrackingNumber = order.getOrderTrackingNumber();
			order.setStatus(orderComplete);
			orderDao.save(order);
			response.addObject("orderTrackingNumber", orderTrackingNumber);
		}

		response.setViewName("cart/orderconfirmation");

		return response;
	}

	@RequestMapping(value = "/orderHistory", method = RequestMethod.GET)
	public ModelAndView orderHistory(@RequestParam (required = false) Long orderId ) throws Exception {

		ModelAndView response = new ModelAndView();
		response.setViewName("cart/orderHistory");

		User user = onlineStoreUtilities.getUserInSession();

		if(user != null ){
			List<Order> orderHistoryList = orderDao.findByUserId(user.getId());
			response.addObject("orderHistoryList",orderHistoryList);
		}

		BigDecimal totalOrderPrice = new BigDecimal(0);

		if(orderId != null){
			List<OrderItem> orderItemList = orderItemDao.findByOrderId(orderId);
			response.addObject("orderId", orderId);
			response.addObject("orderItemListKey", orderItemList);

			Order order = orderDao.findByOrderId(orderId).get(0);
			totalOrderPrice = order.getTotalPrice();
			response.addObject("totalOrderPrice", totalOrderPrice);
		}

		return response;
	}

}
