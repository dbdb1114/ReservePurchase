package com.reservation.orderservice.entity;

public enum OrderStatus {
    BEFOREPAY ,
    PREPARE,
    SHIPPING,
    DELIVERED,
    CONFIRMPURCHASE,
    WITHDRAWAL,
    APPLYREFUND,
    REFUNDCOMPLETE;

    public static OrderStatus[] notIssueUpdate = {PREPARE, SHIPPING, DELIVERED};
}
