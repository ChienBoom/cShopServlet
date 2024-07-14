/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModelDtos;

import Models.*;
import java.util.Date;

/**
 *
 * @author MinhChien
 */
public class UserDto extends AccountDto{
    private long Id;
    private String FullName;
    private String Email;
    private String Sex;
    private String Address;
    private String PictureUrl;
    private Date DOB;
    private long AccountId;
    
    public UserDto(){
        
    }
    
    public UserDto(long id, String fullName, String email, String sex, String address, String pictureUrl, Date dob, long accountId){
        this.Id = id;
        this.FullName = fullName;
        this.Email = email;
        this.Sex = sex;
        this.Address = address;
        this.PictureUrl = pictureUrl;
        this.DOB = dob;
        this.AccountId = accountId;
    }
    // Getter and Setter for Id
    public long getId() {
        return Id;
    }

    // Getter and Setter for FullName
    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        this.FullName = fullName;
    }

    // Getter and Setter for Email
    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        this.Email = email;
    }

    // Getter and Setter for Sex
    public String getSex() {
        return Sex;
    }

    public void setSex(String sex) {
        this.Sex = sex;
    }

    // Getter and Setter for Address
    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        this.Address = address;
    }

    // Getter and Setter for PictureUrl
    public String getPictureUrl() {
        return PictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.PictureUrl = pictureUrl;
    }

    // Getter and Setter for DOb
    public Date getDOB() {
        return DOB;
    }

    public void setDOB(Date dob) {
        this.DOB = dob;
    }

    // Getter and Setter for AccountId
    public long getAccountId() {
        return AccountId;
    }

    public void setAccountId(long accountId) {
        this.AccountId = accountId;
    }
}
