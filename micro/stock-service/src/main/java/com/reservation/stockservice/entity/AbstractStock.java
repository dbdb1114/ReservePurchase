package com.reservation.stockservice.entity;

public abstract class AbstractStock implements Stock{
    abstract public Long getProductId();
    abstract public AbstractStock decreaseInventory(Integer amount) throws Exception;
    abstract public AbstractStock increaseInventory(Integer amount);
}
