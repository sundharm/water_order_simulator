package com.restwatersimulator.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.restwatersimulator.service.OrderService;
import com.restwatersimulator.service.impl.OrderServiceImpl;
import com.restwatersimulator.model.OrderDetails;

@RestController
public class OrderController {
	
			//end point to receive and place a new order
			@PostMapping(path="/placeOrder")
		    public OrderDetails placeOrder(@RequestBody OrderDetails orderDetail) {
		        OrderService ordersService = new OrderServiceImpl();
		        return ordersService.placeOrder(orderDetail);
		    }
			
			//end point to display all orders by getting id in parameter
		    @GetMapping("/viewOrders")
		    public List<OrderDetails> viewOrders(@RequestParam(value = "search", required=true) String search,
		            @RequestParam(value = "searchValue", required=false) String searchValue) {
		        OrderService ordersService = new OrderServiceImpl();
		        return ordersService.viewOrders(search, searchValue);
		    }
		    
		    //end point to cancel an order
		    @GetMapping("/cancelOrder")
		    public Object cancelOrder(@RequestParam(value = "order_id", required=true) Long order_id) {

		        OrderService ordersService = new OrderServiceImpl();
		        String response = null;
		        if (order_id == null || order_id < 0) {
		            response = "Please check the order ID";
		        } else {
		            response = ordersService.cancelOrder(order_id);
		        }
		        return response;
		    }

}
