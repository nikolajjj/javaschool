package com.tsystems.entity;

import com.tsystems.dto.enums.CargoStatus;

import javax.persistence.*;

/**
 * Cargo entity
 */

@Entity
@Table(name = "cargo")
public class Cargo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "description")
    private String description;

    @Column(name = "weight")
    private Integer weight;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private CargoStatus status;

//    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "city_from")
    private City city_from;

//    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "city_to")
    private City city_to;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    public Cargo() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public CargoStatus getStatus() {
        return status;
    }

    public void setStatus(CargoStatus status) {
        this.status = status;
    }

    public City getCity_from() {
        return city_from;
    }

    public void setCity_from(City city_from) {
        this.city_from = city_from;
    }

    public City getCity_to() {
        return city_to;
    }

    public void setCity_to(City city_to) {
        this.city_to = city_to;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "Cargo{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", weight=" + weight +
                ", status=" + status +
                ", city_from=" + city_from +
                ", city_to=" + city_to +
                '}';
    }
}
