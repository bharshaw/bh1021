package domain;

import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Data
public class RentalAgreement {
    public RentalAgreement(String toolCode, String toolType, String checkOut, String finalCharge, String discount, String numberOfRentalDays) {
        this.toolCode = toolCode;
        this.toolType = toolType;
        this.checkOut = checkOut;
        this.finalCharge = finalCharge;
        this.discount = discount;
        this.numberOfRentalDays = numberOfRentalDays;
    }

    String toolCode;
    String toolType;
    String checkOut;
    String finalCharge;
    String discount;
    String numberOfRentalDays;
    String dateDueBack;

    public String formatCheckoutDate(){
        return checkOut;
    }

    public String calculateReturnDate(){
        SimpleDateFormat format1=new SimpleDateFormat("MM/dd/yy");

        Date dt1 = new Date();
        Calendar c = Calendar.getInstance();
        String returnDate="";
        try {
            dt1 = format1.parse(checkOut);
        }catch(Exception e){
            e.printStackTrace();
        }
        c.setTime(dt1);
        c.add(Calendar.DATE, 5);
        dt1 = c.getTime();

        try{
            //returnDate = dt1.toString();
            returnDate = format1.format(dt1);
        } catch (Exception e){

        }

        return returnDate;
    }

    @Override
    public String toString() {
        return
        "Tool code               " + toolCode +"\n"+
        "Tool type               " + toolType + "\n"+
        "CheckOut date           " + formatCheckoutDate() +"\n"+
        "Number of rental days   " + numberOfRentalDays +"\n" +
        "Return date             " + calculateReturnDate() +"\n"+
        "Discount                " + discount +"%\n"+
        "Final charge            " + "$"+finalCharge;
    }

}
