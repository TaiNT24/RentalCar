/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taint.model.account;

import java.io.Serializable;

/**
 *
 * @author nguye
 */
public class AccountDTO implements Serializable{
    private String email;
    private String password;
    private String name;
    private String address;
    private String phone;
    private String createDate;
    private String status = "New";
    
    private boolean role = false;       //1:admin, 0:member

    public AccountDTO() {
    }

    public AccountDTO(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }
    

    public AccountDTO(String email, String password, String name
            , String address, String phone, String createDate) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.createDate = createDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isRole() {
        return role;
    }

    public void setRole(boolean role) {
        this.role = role;
    }

    
    
}
