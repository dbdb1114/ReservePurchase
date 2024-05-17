package com.reservation.productservice.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.NoSuchElementException;
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

    @JsonBackReference
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRODUCT_ID", nullable = false)
    private Product product;

    public void setProduct(Product product) {
        this.product = product;
    }

    public void decreaseInventory(int quantity){
        if(this.inventory <= 0){
            throw new NoSuchElementException();
        }
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
