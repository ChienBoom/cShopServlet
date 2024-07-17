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
public class ProductDetail {
    private long Id;
    private String Size;
    private String Color;
    private String PictureUrl;
    private BigDecimal Price;
    private int QuantitySold;
    private int QuantityStock;
    private String Description;
    private long ProductId;
    private Product Product;
    
    
    public ProductDetail(){}
    
    public ProductDetail(long id, String size, String color, String pictureUrl, BigDecimal price, int quantitySold, int quantityStock, String description, long productId){
        this.Id = id;
        this.Size = size;
        this.Color = color;
        this.PictureUrl = pictureUrl;
        this.QuantitySold = quantitySold;
        this.QuantityStock = quantityStock;
        this.Price = price;
        this.ProductId = productId;
        this.Description = description;
    }
    
    // Getter and Setter for Id
    public long getId() {
        return Id;
    }
    
    public void setId(long id){
        this.Id = id;
    }

    // Getter and Setter for Size
    public String getSize() {
        return Size;
    }

    public void setSize(String size) {
        this.Size = size;
    }

    // Getter and Setter for Color
    public String getColor() {
        return Color;
    }

    public void setColor(String color) {
        this.Color = color;
    }

    public String getPictureUrl() {
        return PictureUrl;
    }

    public void setPictureUrl(String PictureUrl) {
        this.PictureUrl = PictureUrl;
    }
    
    // Getter and Setter for Price
    public BigDecimal getPrice() {
        return Price;
    }

    public void setPrice(BigDecimal price) {
        this.Price = price;
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

    // Getter and Setter for Description
    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        this.Description = description;
    }

    // Getter and Setter for ProductId
    public long getProductId() {
        return ProductId;
    }

    public void setProductId(long productId) {
        this.ProductId = productId;
    }

    public Product getProduct() {
        return Product;
    }

    public void setProduct(Product Product) {
        this.Product = Product;
    }
    
}
