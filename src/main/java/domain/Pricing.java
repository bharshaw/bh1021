package domain;

import lombok.Data;

@Data
public class Pricing {
    public Pricing(String toolType, double dailyCharge, boolean weekDayCharge, boolean weekendCharge, boolean holidayCharge) {
        this.toolType = toolType;
        this.dailyCharge = dailyCharge;
        this.weekDayCharge = weekDayCharge;
        this.weekendCharge = weekendCharge;
        this.holidayCharge = holidayCharge;
    }

    String toolType;
    double dailyCharge;
    boolean weekDayCharge;
    boolean weekendCharge;
    boolean holidayCharge;


}
