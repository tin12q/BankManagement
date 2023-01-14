package tin.bank.client.model;

import java.sql.Date;

public class Account {
    private String id;
    private String fname;
    private String lname;
    private Double balance;
    private String email;
    private String phone;
    private String address;
    private Date DOB;
    private String city;
    private String state;
    private String zipCode;

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public Date getDOB() {
        return DOB;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public Account(String id, String fname, String lname, Double balance, String email, String phone, String address,
            Date dateOfBirth, String city, String state, String zipCode, String username) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.balance = balance;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.DOB = dateOfBirth;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.username = username;
    }

    public Account(String id, String fname, String lname, String username) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.username = username;
    }

    private String username;

    public String getUsername() {
        return username;
    }

    public Account(String username) {
        this.username = username;
    }

    public String getId() {
        return id;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public String toString() {
        // return "Account is " + this.id + " name " + this.name + " balance " +
        // this.balance;
        return this.fname + " " + this.lname;
    }

}
