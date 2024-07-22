/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author MinhChien
 */
public class RevenueData {
    private List<String> Labels;
    private List<BigDecimal> Data;

    public RevenueData(List<String> Labels, List<BigDecimal> Data) {
        this.Labels = Labels;
        this.Data = Data;
    }

    public RevenueData() {
    }

    public List<String> getLabels() {
        return Labels;
    }

    public void setLabels(List<String> Labels) {
        this.Labels = Labels;
    }

    public List<BigDecimal> getData() {
        return Data;
    }

    public void setData(List<BigDecimal> Data) {
        this.Data = Data;
    }
    
    
}
