package com.eelex.ironpowerfx.templates;

import org.json.simple.JSONObject;

public class UserObject {
    private String uid;
    private String email;
    private String firstName;
    private String lastName;
    private String phoneNumber;

    public void setUserInfoFromJSON(JSONObject o) {
        this.uid = String.valueOf(o.get("uid"));
        this.email = String.valueOf(o.get("email"));
        this.firstName = String.valueOf(o.get("firstName"));
        this.lastName = String.valueOf(o.get("lastName"));
        this.phoneNumber = String.valueOf(o.get("phoneNumber"));
    }

    public JSONObject getUserInJSON() {
        JSONObject userObject = new JSONObject();
        userObject.put("uid", uid);
        userObject.put("email", email);
        userObject.put("firstName", firstName);
        userObject.put("lastName", lastName);
        userObject.put("phoneNumber", phoneNumber);
        return userObject;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
