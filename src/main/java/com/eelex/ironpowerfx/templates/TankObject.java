package com.eelex.ironpowerfx.templates;

public class TankObject {
    private String aNumber;
    private String customer;
    private String owner;
    private String location;
    private String buildYear;
    private String size;
    private String other;

    public void printValues() {
        System.out.println(aNumber);
        System.out.println(customer);
        System.out.println(location);
        System.out.println(owner);
        System.out.println(buildYear);
        System.out.println(size);
        System.out.println(other);
    }

    public void setANumber(String aNumber) {
        this.aNumber = aNumber;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setBuildYear(String buildYear) {
        this.buildYear = buildYear;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public String getaNumber() {
        return aNumber;
    }

    public String getCustomer() {
        return customer;
    }

    public String getOwner() {
        return owner;
    }

    public String getLocation() {
        return location;
    }

    public String getBuildYear() {
        return buildYear;
    }

    public String getSize() {
        return size;
    }

    public String getOther() {
        return other;
    }
}
