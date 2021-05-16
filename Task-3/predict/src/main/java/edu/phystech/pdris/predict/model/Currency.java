package edu.phystech.pdris.predict.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Currency {
    @Id
    private String date;
    private double dollar;

    public Currency() {}

    public Currency(String date, double dollar) {
        this.date = date;
        this.dollar = dollar;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getDollar() {
        return dollar;
    }

    public void setDollar(double dollar) {
        this.dollar = dollar;
    }
}
