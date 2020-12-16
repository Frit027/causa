package com.site.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "finance")
public class Finance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "budget")
    private double budget;

    @Column(name = "income")
    private double income;

    @Basic
    @Temporal(TemporalType.DATE)
    private Date month;

    public Finance() {}

    public Finance(double budget, double income, Date month) {
        this.budget = budget;
        this.income = income;
        this.month = month;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public double getIncome() {
        return income;
    }

    public void setIncome(double income) {
        this.income = income;
    }

    public Date getMonth() {
        return month;
    }

    public void setMonth(Date month) {
        this.month = month;
    }
}
