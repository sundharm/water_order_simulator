package com.restwatersimulator.service;

import java.util.List;

import com.restwatersimulator.model.OrderDetails;

public interface OrderService {
    OrderDetails placeOrder(OrderDetails orderDetail);
    List<OrderDetails> viewOrders(String search, String searchByValue);
    String cancelOrder(Long orderId);
}