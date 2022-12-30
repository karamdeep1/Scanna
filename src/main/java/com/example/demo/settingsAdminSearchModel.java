package com.example.demo;

public class settingsAdminSearchModel
{
    String settingsAdminEmployeeID;
    Integer settingsAdminClearance;
    String settingsAdminEmail, settingsAdminName;

    public settingsAdminSearchModel(String settingsAdminEmployeeID, Integer settingsAdminClearance, String settingsAdminEmail, String settingsAdminName) {
        this.settingsAdminEmployeeID = settingsAdminEmployeeID;
        this.settingsAdminClearance = settingsAdminClearance;
        this.settingsAdminEmail = settingsAdminEmail;
        this.settingsAdminName = settingsAdminName;
    }

    public String getSettingsAdminEmployeeID() {
        return settingsAdminEmployeeID;
    }

    public void setSettingsAdminEmployeeID(String settingsAdminEmployeeID) {
        this.settingsAdminEmployeeID = settingsAdminEmployeeID;
    }

    public Integer getSettingsAdminClearance() {
        return settingsAdminClearance;
    }

    public void setSettingsAdminClearance(Integer settingsAdminClearance) {
        this.settingsAdminClearance = settingsAdminClearance;
    }

    public String getSettingsAdminEmail() {
        return settingsAdminEmail;
    }

    public void setSettingsAdminEmail(String settingsAdminEmail) {
        this.settingsAdminEmail = settingsAdminEmail;
    }

    public String getSettingsAdminName() {
        return settingsAdminName;
    }

    public void setSettingsAdminName(String settingsAdminName) {
        this.settingsAdminName = settingsAdminName;
    }
}
