/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author MinhChien
 */
public class AddToCart {
    private long Id;
    private String Username;
    private long ProductDetailId;
    private int Quantity;
    private ProductDetail ProductDetail;

    public AddToCart(long id, String Username, long ProductDetailId, int Quantity) {
        this.Id = id;
        this.Username = Username;
        this.ProductDetailId = ProductDetailId;
        this.Quantity = Quantity;
    }

    public AddToCart() {
    }

    public long getId() {
        return Id;
    }

    public void setId(long Id) {
        this.Id = Id;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }

    public long getProductDetailId() {
        return ProductDetailId;
    }

    public void setProductDetailId(long ProductDetailId) {
        this.ProductDetailId = ProductDetailId;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int Quantity) {
        this.Quantity = Quantity;
    }

    public ProductDetail getProductDetail() {
        return ProductDetail;
    }

    public void setProductDetail(ProductDetail ProductDetail) {
        this.ProductDetail = ProductDetail;
    }
    
}
