package com.example.demo;

public class BarcodeSearchModel {
    String item_ID, description;
    Integer clearance;
    String type;
    String location;
    String employee_ID;

    public BarcodeSearchModel(String item_ID, String employee_ID, Integer clearance, String type, String location, String description) {
        this.item_ID = item_ID;
        this.employee_ID = employee_ID;
        this.clearance = clearance;
        this.type = type;
        this.location = location;
        this.description = description;
    }

    public String getItem_ID() {
        return item_ID;
    }

    public void setItem_ID(String item_ID) {
        this.item_ID = item_ID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getClearance() {
        return clearance;
    }

    public void setClearance(Integer clearance) {
        this.clearance = clearance;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getEmployee_ID() {
        return employee_ID;
    }

    public void setEmployee_ID(String employee_ID) {
        this.employee_ID = employee_ID;
    }
}
