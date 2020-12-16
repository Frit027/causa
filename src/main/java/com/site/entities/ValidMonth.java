package com.site.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "valid_month")
public class ValidMonth {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Basic
    @Temporal(TemporalType.DATE)
    private Date month;

    public ValidMonth() {}

    public ValidMonth(Date month) {
        this.month = month;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getMonth() {
        return month;
    }

    public void setMonth(Date month) {
        this.month = month;
    }
}
