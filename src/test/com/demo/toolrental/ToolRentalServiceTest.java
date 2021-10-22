package com.demo.toolrental;

import domain.Pricing;
import domain.RentalAgreement;
import domain.Tool;
import exceptions.RentalException;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;


class ToolRentalServiceTest {

    ToolRentalService trs = new ToolRentalService();
    Map<String, Pricing> pricing;
    HashMap<String, Tool> tools;

        @BeforeEach
        public void loadData(){
            pricing = trs.loadPricing();
            tools = trs.loadTools();
        }


    @Test
    public void test1() throws RentalException {
        Reservation res = new Reservation("JAKR",pricing.get("JAKR"),101,5,"09/03/15");
        ToolRentalService s = new ToolRentalService();
        double price = s.calculatePrice(res) ;
        RentalAgreement ra = new RentalAgreement(res.getToolCode(),pricing.get(res.getToolCode()).getToolType(),
                res.getPickUpDate(),String.valueOf(price),String.valueOf(res.getDiscount()),String.valueOf(res.getNumDayRental()));
        System.out.println("----------------------------------test1-------------------------------------------");
        System.out.println(ra.toString());
        Assertions.assertTrue(price == 8.97);
    }

    @Test
    public void test2() throws RentalException {
        Reservation res = new Reservation("LADW",pricing.get("LADW"),10,3,"07/02/20");
        ToolRentalService s = new ToolRentalService();
        double price = s.calculatePrice(res);
        RentalAgreement ra = new RentalAgreement(res.getToolCode(),pricing.get(res.getToolCode()).getToolType(),
                res.getPickUpDate(),String.valueOf(price),String.valueOf(res.getDiscount()),String.valueOf(res.getNumDayRental()));
        System.out.println("---------------------------------test2--------------------------------------------");
        System.out.println(ra.toString());
        Assertions.assertTrue(price == 3.58);
    }

    @Test
    public void test3() throws RentalException {
        Reservation res = new Reservation("CHNS",pricing.get("CHNS"),25,5,"07/02/15");
        ToolRentalService s = new ToolRentalService();
        double price = s.calculatePrice(res) ;
        RentalAgreement ra = new RentalAgreement(res.getToolCode(),pricing.get(res.getToolCode()).getToolType(),
                res.getPickUpDate(),String.valueOf(price),String.valueOf(res.getDiscount()),String.valueOf(res.getNumDayRental()));
        System.out.println("---------------------------------test3--------------------------------------------");
        System.out.println(ra.toString());
        Assertions.assertTrue(price == 3.35);
    }

    @Test
    public void test4() throws RentalException {
        Reservation res = new Reservation("JAKD",pricing.get("JAKD"),0,6,"09/03/15");
        ToolRentalService s = new ToolRentalService();
        double price = s.calculatePrice(res) ;
        RentalAgreement ra = new RentalAgreement(res.getToolCode(),pricing.get(res.getToolCode()).getToolType(),
                res.getPickUpDate(),String.valueOf(price),String.valueOf(res.getDiscount()),String.valueOf(res.getNumDayRental()));
        System.out.println("----------------------------------test4-------------------------------------------");
        System.out.println(ra.toString());
        Assertions.assertTrue(price == 8.97);
    }

    @Test
    public void test5() throws RentalException {
        Reservation res = new Reservation("JAKR",pricing.get("JAKR"),0,9,"07/02/15");
        ToolRentalService s = new ToolRentalService();
        double price = s.calculatePrice(res) ;
        RentalAgreement ra = new RentalAgreement(res.getToolCode(),pricing.get(res.getToolCode()).getToolType(),
                res.getPickUpDate(),String.valueOf(price),String.valueOf(res.getDiscount()),String.valueOf(res.getNumDayRental()));
        System.out.println("------------------------------------test5-----------------------------------------");
        System.out.println(ra.toString());
        Assertions.assertTrue(price == 20.93);
    }

    @Test
    public void test6() throws RentalException {
        Reservation res = new Reservation("JAKR",pricing.get("JAKR"),50,4,"07/02/20");
        ToolRentalService s = new ToolRentalService();
        double price = s.calculatePrice(res) ;
        RentalAgreement ra = new RentalAgreement(res.getToolCode(),pricing.get(res.getToolCode()).getToolType(),
                res.getPickUpDate(),String.valueOf(price),String.valueOf(res.getDiscount()),String.valueOf(res.getNumDayRental()));
        System.out.println("------------------------------------test6-----------------------------------------");
        System.out.println(ra.toString());
        Assertions.assertTrue(price == 2.99);
    }





}