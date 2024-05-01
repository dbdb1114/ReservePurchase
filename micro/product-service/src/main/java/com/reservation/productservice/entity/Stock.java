package com.reservation.productservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "STOCK")
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ColumnDefault("0")
    @Column(nullable = false)
    private Integer inventory;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRODUCT_ID", nullable = false)
    private Product product;

    public void setProduct(Product product) {
        this.product = product;
    }

    public void decreaseInventory(int quantity){
        this.inventory -= quantity;
    }

    public void increaseInventory(int quantity){
        this.inventory += quantity;
    }

    @Override
    public String toString() {
        return "Stock{" +
                "id=" + id +
                ", inventory=" + inventory +
                '}';
    }

}
