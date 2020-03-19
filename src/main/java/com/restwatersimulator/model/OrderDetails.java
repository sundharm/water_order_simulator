package com.restwatersimulator.model;

import java.time.LocalDateTime;

public class OrderDetails {

    private long order_id;
    private long farm_id;
    private long order_duration;
    private OrderStatus order_status;
    private String status_description;
    private LocalDateTime dateTime;

    public OrderDetails(long order_id, long farm_id, LocalDateTime dateTime, long order_duration, OrderStatus order_status) {
        this.order_id = order_id;
        this.farm_id = farm_id;
        this.dateTime = dateTime;
        this.order_duration = order_duration;
        this.order_status = order_status;
    }

    public long getOrderId() {
        return order_id;
    }

    public void setOrderId(long orderId) {
        this.order_id = orderId;
    }

    public long getFarmId() {
        return farm_id;
    }

    public void setFarmId(long farmId) {
        this.farm_id = farmId;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public long getDuration() {
        return order_duration;
    }

    public void setDuration(long duration) {
        this.order_duration = duration;
    }

    public OrderStatus getOrderStatus() {
        return order_status;
    }

    public void setOrderStatus(OrderStatus status) {
        this.order_status = status;
    }

    public String getOrderStatusDescription() {
        switch (order_status) {
            case REQUESTED: return "Order has been placed but not yet delivered.";
            case IN_PROGRESS: return "Order is being delivered right now.";
            case DELIVERED: return "Order has been delivered.";
            case CANCELLED: return "Order was cancelled before delivery.";
        }
        return "";
    }

}
