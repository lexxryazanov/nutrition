package com.nexing.nutrition.database.entity;

import javax.persistence.*;

@Entity
@Table(name = "companies")
public class Company {
    @Id
    @SequenceGenerator(name = "company-id-generator", sequenceName = "COMPANY_SEQUENCE")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "company-id-generator")
    private Integer id;

    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
