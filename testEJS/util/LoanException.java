package util;

//package com.as400samplecode.util;

public class LoanException {

boolean active = false;
int customerId = 0;
String firstName = null;
String lastName = null;
String email = null;
String loan_id = null;
String exc_type = null;
String desc;
public String getExcType() {
    return exc_type;
}
public String getLoanId() {
    return loan_id;
}
public String getDesc() {
    return desc;
}

public void setExcType(String in) {
    this.exc_type = in;
}
public void  setLoanId(String in) {
    this.loan_id = in;
}
public void setDesc(String in) {
    this.desc= in ;
}


public boolean isActive() {
    return active;
}
public void setActive(boolean active) {
    this.active = active;
}
public int getCustomerId() {
    return customerId;
}
public void setCustomerId(int customerId) {
    this.customerId = customerId;
}
public String getFirstName() {
    return firstName;
}
public void setFirstName(String firstName) {
    this.firstName = firstName;
}
public String getLastName() {
    return lastName;
}
public void setLastName(String lastName) {
    this.lastName = lastName;
}
public String getEmail() {
    return email;
}
public void setEmail(String email) {
    this.email = email;
}

}


