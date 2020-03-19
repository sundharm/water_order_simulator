package com.restwatersimulator.service.impl;

import java.util.List;

import com.restwatersimulator.dao.OrderDao;
import com.restwatersimulator.dao.impl.OrderDaoImpl;
import com.restwatersimulator.service.OrderService;
import com.restwatersimulator.model.OrderDetails;
import com.restwatersimulator.model.OrderStatus;

public class OrderServiceImpl implements OrderService {

	 	@Override
	    public OrderDetails placeOrder(OrderDetails orderDetail) {
	        OrderDao orderDao = new OrderDaoImpl();
	        return orderDao.placeOrder(orderDetail);
	    }
	 	
	    @Override
	    public List<OrderDetails> viewOrders(String search, String searchByValue) throws UnsupportedOperationException {
	        List<OrderDetails> ordersList = null;
	        OrderDao orderDao = new OrderDaoImpl();
	        if (search.equalsIgnoreCase("all")) {
	            ordersList = orderDao.findAll();
	        } else if (search.equalsIgnoreCase("order_id")) {
	            ordersList = orderDao.findByOrderId(searchByValue);
	        } else if (search.equalsIgnoreCase("farm_id")) {
	            ordersList = orderDao.findByFarmId(searchByValue);
	        } else {
	            throw new UnsupportedOperationException("Invalid operation - " + search);
	        }
	        return ordersList;
	    }

	    @Override
	    public String cancelOrder(Long orderId) {
	        String response = "Order Id " + orderId + " was successfully cancelled.";

	        OrderDao orderDao = new OrderDaoImpl();
	        OrderStatus orderStatus = orderDao.getStatusCodeByOrderId(orderId);
	        if (orderStatus == OrderStatus.REQUESTED) {
	            orderDao.cancelOrder(orderId);
	        } else if (orderStatus == OrderStatus.CANCELLED) {
	            throw new UnsupportedOperationException("Order id " + orderId + " is already cancelled.");
	        } else if (orderStatus == OrderStatus.IN_PROGRESS) {
	            throw new UnsupportedOperationException("Order id " + orderId + " is in progress.");
	        } else if (orderStatus == OrderStatus.DELIVERED) {
	            throw new UnsupportedOperationException("Order id " + orderId + " is already delivered.");
	        }
	        return response;
	    }
}
