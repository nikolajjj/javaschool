package com.tsystems.entity;

import com.tsystems.dto.enums.DriverStatus;

import javax.persistence.*;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Objects;

/**
 * Driver entity
 */
@Entity
@Table(name = "driver")
public class Driver {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "first_name")
    private String first_name;

    @Column(name = "second_name")
    private String second_name;

    @Column(name = "personal_number", unique = true)
    private String personal_number;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private DriverStatus status;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinColumn(name = "current_city")
    private City current_city;

    @ManyToOne(cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinColumn(name = "current_wagon")
    private Wagon current_wagon;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user_id;

    @OneToMany(mappedBy = "driver")
    private List<DriverShift> driverList;

    @PostLoad
    @PostPersist()
    protected void onPersist() {
        String pattern = "0000";
        DecimalFormat myFormatter = new DecimalFormat(pattern);
        String pm = myFormatter.format(getId().intValue());
        setPersonal_number(pm);
    }

    @PreRemove()
    protected void onDelete() {
        for(DriverShift driverShift : driverList) {
            driverShift.setDriver(null);
        }
    }

    public Driver() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getSecond_name() {
        return second_name;
    }

    public void setSecond_name(String second_name) {
        this.second_name = second_name;
    }

    public String  getPersonal_number() {
        return personal_number;
    }

    public void setPersonal_number(String  personal_number) {
        this.personal_number = personal_number;
    }

    public DriverStatus getStatus() {
        return status;
    }

    public void setStatus(DriverStatus status) {
        this.status = status;
    }

    public City getCurrent_city() {
        return current_city;
    }

    public void setCurrent_city(City current_city) {
        this.current_city = current_city;
    }

    public Wagon getCurrent_wagon() {
        return current_wagon;
    }

    public void setCurrent_wagon(Wagon current_wagon) {
        this.current_wagon = current_wagon;
    }

    public User getUser_id() {
        return user_id;
    }

    public void setUser_id(User user_id) {
        this.user_id = user_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Driver driver = (Driver) o;
        return Objects.equals(id, driver.id) &&
                Objects.equals(first_name, driver.first_name) &&
                Objects.equals(second_name, driver.second_name) &&
                Objects.equals(personal_number, driver.personal_number) &&
                status == driver.status &&
                Objects.equals(current_city, driver.current_city) &&
                Objects.equals(current_wagon, driver.current_wagon) &&
                Objects.equals(user_id, driver.user_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, first_name, second_name, personal_number, status, current_city, current_wagon, user_id);
    }

    @Override
    public String toString() {
        return "Driver{" +
                "id=" + id +
                ", first_name='" + first_name + '\'' +
                ", second_name='" + second_name + '\'' +
                ", personal_number='" + personal_number + '\'' +
                ", status=" + status +
                ", current_city=" + current_city +
                ", current_wagon=" + current_wagon +
                ", user_id=" + user_id +
                '}';
    }
}
