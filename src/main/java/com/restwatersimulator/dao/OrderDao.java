package com.restwatersimulator.dao;

import java.util.List;
import com.restwatersimulator.model.OrderDetails;
import com.restwatersimulator.model.OrderStatus;

public interface OrderDao {
	OrderDetails placeOrder(OrderDetails orderDetail);
	OrderStatus getStatusCodeByOrderId(Long order_id);
	void cancelOrder(Long order_id);
    List<OrderDetails> findAll();
    List<OrderDetails> findByOrderId(String order_id);
    List<OrderDetails> findByFarmId(String farm_id); 
}
