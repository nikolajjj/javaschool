package com.tsystems.entity;

import com.tsystems.entity.enums.OrderStatus;
import org.springframework.stereotype.Controller;

import javax.persistence.*;
import java.util.List;

/**
 * Order entity
 */
@Entity
@Table(name = "myorder")
public class Order {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "order_number")
    private Integer order_number;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @OneToOne
    @JoinColumn(name = "wagon")
    private Wagon wagon;

    @OneToMany(mappedBy = "order")
    private List<Cargo> cargoList;

    public Order() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrder_number() {
        return order_number;
    }

    public void setOrder_number(Integer order_number) {
        this.order_number = order_number;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Wagon getWagon() {
        return wagon;
    }

    public void setWagon(Wagon wagon) {
        this.wagon = wagon;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", order_number=" + order_number +
                ", status=" + status +
                ", wagon=" + wagon +
                '}';
    }
}
