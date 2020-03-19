package com.restwatersimulator.data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.restwatersimulator.model.OrderDetails;
import com.restwatersimulator.model.OrderStatus;

public class TestData {

	private List<OrderDetails> ordersList;
    private static TestData instance;

    public static TestData getInstance() {
        if (instance == null) {
            synchronized (TestData.class) {
                instance = new TestData();
            }
        }
        return instance;
    }

    private TestData() {
        ordersList = new ArrayList<OrderDetails>();
	LocalDateTime currentTime = LocalDateTime.now();
        ordersList.add(new OrderDetails(1, 101, currentTime, 10000, OrderStatus.REQUESTED));
        ordersList.add(new OrderDetails(2, 102, currentTime, 20000, OrderStatus.IN_PROGRESS));
        ordersList.add(new OrderDetails(3, 103, currentTime, 30000, OrderStatus.DELIVERED));
        ordersList.add(new OrderDetails(4, 104, currentTime, 40000, OrderStatus.CANCELLED));
        
    }

    public List<OrderDetails> getOrdersList() {
        return ordersList;
    }
}
