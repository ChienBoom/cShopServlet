/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import java.math.BigDecimal;

/**
 *
 * @author MinhChien
 */
public class Revenue {
    private String Label;
    private BigDecimal Value;

    public Revenue(String Label, BigDecimal Value) {
        this.Label = Label;
        this.Value = Value;
    }

    public Revenue() {
    }

    public String getLabel() {
        return Label;
    }

    public void setLabel(String Label) {
        this.Label = Label;
    }

    public BigDecimal getValue() {
        return Value;
    }

    public void setValue(BigDecimal Value) {
        this.Value = Value;
    }
    
    
}
