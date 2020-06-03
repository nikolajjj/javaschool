package com.tsystems.entity;

import com.tsystems.dto.enums.OrderStatus;

import javax.persistence.*;
import java.text.DecimalFormat;
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
    private String order_number;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "wagon")
    private Wagon wagon;

    @OneToMany(mappedBy = "order")
    private List<Cargo> cargoList;

    @PostLoad
    @PostPersist()
    protected void onPersist() {
        String pattern = "000000";
        DecimalFormat myFormatter = new DecimalFormat(pattern);
        String pm = myFormatter.format(getId().intValue());
        setOrder_number(pm);
    }


    public Order() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrder_number() {
        return order_number;
    }

    public void setOrder_number(String order_number) {
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
