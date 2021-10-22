package com.demo.toolrental;

import domain.Pricing;
import domain.Tool;
import lombok.Data;

@Data
public class Reservation {
    public Reservation(String toolCode, Pricing price, int discount, int numDayRental, String pickUpDate) {
        this.toolCode = toolCode;
        this.price = price;
        this.discount = discount;
        this.numDayRental = numDayRental;
        this.pickUpDate = pickUpDate;
    }

    private String toolCode;
    private Pricing price;
    private int discount;
    private int numDayRental;
    private String pickUpDate;
}
