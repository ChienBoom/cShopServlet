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
public class OrderDetail {

    private long Id;
    private long OrderId;
    private long ProductDetailId;
    private int Quantity;
    private BigDecimal TotalPrice;
    private String Description;

    public OrderDetail() {
    }

    public OrderDetail(long id, long orderId, long productDetailId,
            int quantity, BigDecimal totalPrice, String description) {
        this.Id = id;
        this.OrderId = orderId;
        this.ProductDetailId = productDetailId;
        this.Quantity = quantity;
        this.TotalPrice = totalPrice;
        this.Description = description;
    }

    // Getter and Setter for Id
    public long getId() {
        return Id;
    }

    // Getter and Setter for OrderId
    public long getOrderId() {
        return OrderId;
    }

    public void setOrderId(long orderId) {
        this.OrderId = orderId;
    }

    // Getter and Setter for ProductDetailId
    public long getProductDetailId() {
        return ProductDetailId;
    }

    public void setProductDetailId(long productDetailId) {
        this.ProductDetailId = productDetailId;
    }

    // Getter and Setter for Quantity
    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        this.Quantity = quantity;
    }

    // Getter and Setter for TotalPrice
    public BigDecimal getTotalPrice() {
        return TotalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.TotalPrice = totalPrice;
    }

    // Getter and Setter for Description
    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        this.Description = description;
    }
}
