package com.zomato.zomato.DTO;

public class updateUserDTO {
    
    private String name;
    private String email;
    private String password;
    private Long phoneNo;

    public void setEmail(String email) {
        this.email = email;
    }
    public String getEmail() {
        return email;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getPassword() {
        return password;
    }
    public void setPhoneNo(Long phoneNo) {
        this.phoneNo = phoneNo;
    }
    public Long getPhoneNo() {
        return phoneNo;
    }
}
