package domain;

import lombok.Data;

@Data
public class Tool {
    public Tool(String toolType, String brand, String toolCode) {
        this.toolType = toolType;
        this.brand = brand;
        this.toolCode = toolCode;
    }

    String toolType;
    String brand;
    String toolCode;



}
