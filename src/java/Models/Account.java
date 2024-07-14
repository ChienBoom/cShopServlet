/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author MinhChien
 */
public class Account {

    private long Id;
    private String Username;
    private String Password;
    private String Role;
    private boolean IsActive;
    private boolean IsDelete;

    public Account() {
    }

    // Constructor
    public Account(long id, String username, String password, String role, boolean isActive, boolean isDelete) {
        this.Id = id;
        this.Username = username;
        this.Password = password;
        this.Role = role;
        this.IsActive = isActive;
        this.IsDelete = isDelete;
    }

    // Getter and Setter for Id
    public long getId() {
        return Id;
    }


    // Getter and Setter for Username
    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        this.Username = username;
    }

    // Getter and Setter for Password
    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        this.Password = password;
    }

    // Getter and Setter for Role
    public String getRole() {
        return Role;
    }

    public void setRole(String role) {
        this.Role = role;
    }

    // Getter and Setter for IsActive
    public boolean isIsActive() {
        return IsActive;
    }

    public void setIsActive(boolean isActive) {
        this.IsActive = isActive;
    }

    // Getter and Setter for IsDelete
    public boolean isIsDelete() {
        return IsDelete;
    }

    public void setIsDelete(boolean isDelete) {
        this.IsDelete = isDelete;
    }
}
