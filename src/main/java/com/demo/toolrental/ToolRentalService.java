package com.demo.toolrental;

import domain.Pricing;
import domain.Tool;
import exceptions.RentalException;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.zip.DataFormatException;

public class ToolRentalService {

    public HashMap<String,Pricing> loadPricing(){
        HashMap<String, Pricing> pricing = new HashMap<>();
        pricing.put("LADW",new Pricing("Ladder",1.99,true, true, false));
        pricing.put("CHNS",new Pricing("Chainsaw",1.49,true, false, true));
        pricing.put("JAKR",new Pricing("Jackhammer",2.99,true, false, false));
        pricing.put("JAKD",new Pricing("Jackhammer",2.99,true, false, false));

        return pricing;
    }

    public HashMap<String,Tool> loadTools(){

    HashMap<String, Tool> tools = new HashMap<>();
        tools.put("LADW",new Tool("Ladder","Werner","LADW"));
        tools.put("CHNS",new Tool("Chainsaw","Stihl","CHNS"));
        tools.put("JAKR",new Tool("Jackhammer","Ridgid","JAKR"));
        tools.put("JAKD",new Tool("Jackhammer","Dewalt","JAKD"));
        return tools;
    }

    public int freeDays(String input_date, int numDays, ArrayList<Integer> days, boolean holidayFree){
        int freeDays = 0;
        SimpleDateFormat format1=new SimpleDateFormat("MM/dd/yy");
        Date dt1 = new Date();
        String year = input_date.substring(6,8);
        String fourthOfJuly = "07/04/"+year;
        Calendar laborDay = laborDay(year);
        Date fourth;
        Calendar fourthCal = Calendar.getInstance();

        try {
            fourth = format1.parse(fourthOfJuly);
            fourthCal.setTime(fourth);
            dt1 = format1.parse(input_date);
        }catch(Exception e){
            e.printStackTrace();
        }

        Calendar c = Calendar.getInstance();
        c.setTime(dt1);
        for(int i = 0; i< numDays; i++) {

            int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
            if (days.contains(dayOfWeek)){
                freeDays += 1;
            }else if(holidayFree && c.equals(fourthCal)){
                freeDays+=1;
            }else if(holidayFree && c.equals(laborDay)){
                freeDays+=1;
            }
            c.add(Calendar.DATE, 1);
        }
        return freeDays;
    }

    public Calendar laborDay(String year){
        SimpleDateFormat format1=new SimpleDateFormat("MM/dd/yy");
        Date dt1 = new Date();
        String input_date = "09/01/"+year;

        try {
            dt1 = format1.parse(input_date);
        }catch(Exception e){
            e.printStackTrace();
        }

        Calendar c = Calendar.getInstance();
        c.setTime(dt1);
        int monday = 0;
        int dayOfWeek;
        for(int i = 0; i<31; i++){
            dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
            if(dayOfWeek==2) {
                monday+=1;
            }

            if(monday==1)break;
            c.add(Calendar.DATE, 1);
        }
        return c;
    }

    public double calculatePrice(Reservation res) throws RentalException{

        ArrayList<Integer> days = new ArrayList<>();
        int chargeDays =  res.getNumDayRental();

        if(chargeDays < 1)   throw new RentalException("The number of rental days must be 1 or more.");

        if(res.getDiscount() < 0 || res.getDiscount() > 100) throw new RentalException("Applied discount must be between 0 and 100 inclusive");

        double percent = res.getDiscount()/(double)100;
        double discount;
        double price = 0.0;
        //no charge at all
        if(!res.getPrice().isHolidayCharge() && !res.getPrice().isWeekDayCharge()  && !res.getPrice().isWeekendCharge()){
            price = 0.0;
        }
        //charge on everyday
        else if(res.getPrice().isHolidayCharge() && res.getPrice().isWeekDayCharge()  && res.getPrice().isWeekendCharge()){
            price = res.getNumDayRental()*res.getPrice().getDailyCharge();
        }else {
            if(!res.getPrice().isWeekDayCharge()) days.addAll(Arrays.asList(2,3,4,5,6));
            if(!res.getPrice().isWeekendCharge()) days.addAll(Arrays.asList(1,7));

            if(!res.getPrice().isHolidayCharge()){
                chargeDays -=freeDays(res.getPickUpDate(), res.getNumDayRental(), days, true);
            }else{
                chargeDays -=freeDays(res.getPickUpDate(), res.getNumDayRental(), days, false);
            }
            price = chargeDays*res.getPrice().getDailyCharge();

        }
        if(res.getDiscount()>0) {
            discount = calculateDiscount(price, percent);
            price = price - discount;
            double rounded = Math.round(price*100.0)/100.0;
            return rounded;
        }

        return  Math.round(price*100.0)/100.0;

    }

    public double calculateDiscount(double amount, double percent){

        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        amount = Double.valueOf(df.format(amount));
        amount = Double.valueOf(df.format(amount*percent));

        return amount;
    }


    public static void main(String[] args){
    }
}
