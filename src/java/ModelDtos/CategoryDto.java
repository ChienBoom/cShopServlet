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
public class CategoryDto {
    private long Id;
    private String Name;
    private String PictureUrl;
    private int NumberProduct;
    private String Description;
    private boolean IsDelete;
    
    public CategoryDto(){}
    
    public CategoryDto(long id, String name, String pictureUrl, int numberProduct, String description, boolean isDelete){
        this.Id = id;
        this.Name = name;
        this.PictureUrl = pictureUrl;
        this.NumberProduct = numberProduct;
        this.Description = description;
        this.IsDelete = isDelete;
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

    // Getter and Setter for NumberProduct
    public int getNumberProduct() {
        return NumberProduct;
    }

    public void setNumberProduct(int numberProduct) {
        this.NumberProduct = numberProduct;
    }

    // Getter and Setter for Description
    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        this.Description = description;
    }

    // Getter and Setter for IsDelete
    public boolean isIsDelete() {
        return IsDelete;
    }

    public void setIsDelete(boolean isDelete) {
        this.IsDelete = isDelete;
    }
}
