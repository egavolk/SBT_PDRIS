package edu.phystech.pdris.hw.model;

public class Currency {
    private String date;
    private double dollar;

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
