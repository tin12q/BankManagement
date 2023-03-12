package tin.bank.server.model;

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
    private String username;
    private String password;
    public Account(String id, String fname, String lname, Double balance, String email, String phone, String address,
                   Date dateOfBirth, String city, String state, String zipCode, String username, String password) {
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
        this.password = password;
    }
    public Account(String id, String fname, String lname, String username) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.username = username;
    }
    public Account(String username) {
        this.username = username;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {

        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public void setStringDob(String dob) {
        // format string to date

        this.DOB = Date.valueOf(dob);

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getDOB() {
        return DOB;
    }

    public void setDOB(Date dOB) {
        DOB = dOB;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
        return this.id + " " + this.fname + " " + this.lname + " " + this.balance + " " + this.email + " " + this.phone
                + " "
                + this.address + " " + this.DOB + " " + this.city + " " + this.state + " " + this.zipCode + " "
                + this.username + " " + this.password;
    }

}
