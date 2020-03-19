package com.restwatersimulator.dao.impl;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;
import java.lang.UnsupportedOperationException;

import com.restwatersimulator.dao.OrderDao;
import com.restwatersimulator.data.TestData;
import com.restwatersimulator.model.OrderDetails;
import com.restwatersimulator.model.OrderStatus;

public class OrderDaoImpl implements OrderDao {
	
	//function to place order
	@Override
	public OrderDetails placeOrder(OrderDetails orderDetail) {
			//if data time is null, set the current date time to the order
	        if (orderDetail.getDateTime() == null) {
	            orderDetail.setDateTime(LocalDateTime.now());
	        }
	        //if date time does not overlap with any other order
	        if (!isOrderOverlapping(orderDetail)) {
	            orderDetail.setOrderStatus(OrderStatus.REQUESTED);
	            getTestData().add(orderDetail);
	        } else {
	            throw new UnsupportedOperationException("Order overlaps, Please try different Date and Time.");
	        }
	        return orderDetail;
	    }

	//get all the orders
    private List<OrderDetails> getTestData() {
        return TestData.getInstance().getOrdersList();
    }

    @Override
    public List<OrderDetails> findAll() {
        return getTestData();
    }

    @Override
    public List<OrderDetails> findByOrderId(String order_id) {
        return getTestData().stream()
                             .filter(order -> order.getOrderId() == Long.valueOf(order_id))
                             .collect(Collectors.toList());
    }

    @Override
    public List<OrderDetails> findByFarmId(String farm_id) {
        return getTestData().stream()
                             .filter(order -> order.getFarmId() == Long.valueOf(farm_id))
                             .collect(Collectors.toList());
    }

    @Override
    public OrderStatus getStatusCodeByOrderId(Long order_id) {
        OrderStatus status = null;
        if (findByOrderId(order_id.toString()).get(0) != null) {
            status = findByOrderId(order_id.toString()).get(0).getOrderStatus();
        }
        return status;
    }

    //function to cancel an order
    @Override
    public void cancelOrder(Long order_id) {
        if (findByOrderId(order_id.toString()).get(0) != null) {
            findByOrderId(order_id.toString()).get(0).setOrderStatus(OrderStatus.CANCELLED);
        }
    }
    
    //function to test overlapping of orders
    private boolean isOrderOverlapping(OrderDetails orderDetail) { 

        boolean isOverlapping = false;
        //get the new order's start date and end date
        LocalDateTime newStartDate = orderDetail.getDateTime();
        LocalDateTime newEndDate = newStartDate.plus(orderDetail.getDuration(), ChronoUnit.MILLIS);

        //get all orders and their start dates and end dates
        List<OrderDetails> ordersList = findByFarmId(String.valueOf(orderDetail.getFarmId()));
        if (ordersList.size() > 0) {
            for (OrderDetails order : ordersList) {
                LocalDateTime oldStartDate = order.getDateTime();
                LocalDateTime oldEndDate = oldStartDate.plus(order.getDuration(), ChronoUnit.MILLIS);
                //for each old order, test if it overlaps with new order
                isOverlapping = isOverlapping(newStartDate, newEndDate, oldStartDate, oldEndDate);
                if (isOverlapping) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isOverlapping(LocalDateTime newStartDate, LocalDateTime newEndDate, LocalDateTime oldStartDate, LocalDateTime oldEndDate) {
        //isbefore to test order of dates
    	return newStartDate.isBefore(oldEndDate) && oldStartDate.isBefore(newEndDate);
    }
}
