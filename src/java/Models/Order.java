/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

/**
 *
 * @author MinhChien
 */
public class Order {

    private long Id;
    private LocalDateTime OrderAt;
    private BigDecimal TotalPrice;
    private String Description;
    private long UserId;
    private boolean IsDelete;
    private String Status;
    private User User;

    public Order() {

    }

    public Order(long Id, LocalDateTime OrderAt, BigDecimal TotalPrice, String Description, long UserId, boolean IsDelete, String Status) {
        this.Id = Id;
        this.OrderAt = OrderAt;
        this.TotalPrice = TotalPrice;
        this.Description = Description;
        this.UserId = UserId;
        this.IsDelete = IsDelete;
        this.Status = Status;
    }

    // Getter and Setter for Id
    public long getId() {
        return Id;
    }

    // Getter and Setter for OrderAt
    public LocalDateTime getOrderAt() {
        return OrderAt;
    }

    public void setOrderAt(LocalDateTime orderAt) {
        this.OrderAt = orderAt;
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

    // Getter and Setter for UserId
    public long getUserId() {
        return UserId;
    }

    public void setUserId(long userId) {
        this.UserId = userId;
    }
    
    public boolean getIsDelete() {
        return IsDelete;
    }

    public void setIsDelete(boolean IsDelete) {
        this.IsDelete = IsDelete;
    }

    public User getUser() {
        return User;
    }

    public void setUser(User User) {
        this.User = User;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }
    
}
