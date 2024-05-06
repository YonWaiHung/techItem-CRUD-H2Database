package com.example.tech.model;

import jakarta.persistence.*;

@Entity
@Table(name = "techItems")
public class Tech{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id = 0;

    @Column(name = "techType")
    private String techType;

    @Column(name = "techName")
    private String techName;

    @Column(name = "amount")
    private long amount;

    public Tech() {

    }

    public Tech(String techType, String techName, long amount) {
        this.techType = techType;
        this.techName = techName;
        this.amount = amount;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTechType() {
        return techType;
    }

    public void setTechType(String techType) {
        this.techType = techType;
    }

    public String getTechName() {
        return techName;
    }

    public void setTechName(String techName) {
        this.techName = techName;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Tech [id=" + id + ", techType=" + techType + ", techName=" + techName + ", amount=" + amount + "]";
    }
}
