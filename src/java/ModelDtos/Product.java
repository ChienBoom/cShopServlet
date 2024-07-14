/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModelDtos;

import Models.*;

/**
 *
 * @author MinhChien
 */
public class Product {
    private long Id;
    private String Name;
    private String PictureUrl;
    private int QuantitySold;
    private int QuantityStock;
    private long CategoryId;
    
    public Product(){}
    
    public Product(long id, String name, String pictureUrl, int quantitySold, int quantityStock, long categoryId){
        this.Id = id;
        this.Name = name;
        this.PictureUrl = pictureUrl;
        this.QuantitySold = quantitySold;
        this.QuantityStock = quantityStock;
        this.CategoryId = categoryId;
    }
    
    // Getter and Setter for Id
    public long getId() {
        return Id;
    }

    // Getter and Setter for Name
    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    // Getter and Setter for PictureUrl
    public String getPictureUrl() {
        return PictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.PictureUrl = pictureUrl;
    }

    // Getter and Setter for QuantitySold
    public int getQuantitySold() {
        return QuantitySold;
    }

    public void setQuantitySold(int quantitySold) {
        this.QuantitySold = quantitySold;
    }

    // Getter and Setter for QuantityStock
    public int getQuantityStock() {
        return QuantityStock;
    }

    public void setQuantityStock(int quantityStock) {
        this.QuantityStock = quantityStock;
    }

    // Getter and Setter for CategoryId
    public long getCategoryId() {
        return CategoryId;
    }

    public void setCategoryId(long categoryId) {
        this.CategoryId = categoryId;
    }
}
