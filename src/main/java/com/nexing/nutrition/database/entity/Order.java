package com.nexing.nutrition.database.entity;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @SequenceGenerator(name = "order-id-generator", sequenceName = "ORDER_SEQUENCE")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order-id-generator")
    private Integer id;

    @NotNull
    @Size(max = 255)
    @Column(name = "user_name")
    private String userName;

    @Column(name = "product_id")
    private Integer productId;

    @NotNull
    @Size(max = 255)
    private String name;

    @NotNull
    @Column(precision = 2)
    @Digits(integer = 8, fraction = 2)
    private Float calorie;

    @NotNull
    @Column(precision = 2)
    @Digits(integer = 8, fraction = 2)
    private Float fats;

    @NotNull
    @Column(precision = 2)
    @Digits(integer = 8, fraction = 2)
    private Float proteins;

    @NotNull
    @Column(precision = 2)
    @Digits(integer = 8, fraction = 2)
    private Float carbohydrates;

    @Column(name = "company_id")
    private Integer companyId;

    @Size(max = 255)
    @Column(name = "company_name")
    private String companyName;

    @Enumerated()
    private OrderState state;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getCalorie() {
        return calorie;
    }

    public void setCalorie(Float calorie) {
        this.calorie = calorie;
    }

    public Float getFats() {
        return fats;
    }

    public void setFats(Float fats) {
        this.fats = fats;
    }

    public Float getProteins() {
        return proteins;
    }

    public void setProteins(Float proteins) {
        this.proteins = proteins;
    }

    public Float getCarbohydrates() {
        return carbohydrates;
    }

    public void setCarbohydrates(Float carbohydrates) {
        this.carbohydrates = carbohydrates;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public OrderState getState() {
        return state;
    }

    public void setState(OrderState state) {
        this.state = state;
    }

    public void apply(Product product) {
        product.setName(getName());
        product.setCalorie(getCalorie());
        product.setFats(getFats());
        product.setProteins(getProteins());
        product.setCarbohydrates(getCarbohydrates());
    }
}
