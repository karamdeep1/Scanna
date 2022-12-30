package com.example.demo;

public class SettingsSearchModel
{
    String settingsBarcodeID;
    Integer settingsClearance;
    String settingsType, settingsLocation, settingsDescription;

    public SettingsSearchModel(String settingsBarcodeID, Integer settingsClearance, String settingsType, String settingsLocation, String settingsDescription) {
        this.settingsBarcodeID = settingsBarcodeID;
        this.settingsClearance = settingsClearance;
        this.settingsType = settingsType;
        this.settingsLocation = settingsLocation;
        this.settingsDescription = settingsDescription;
    }

    public String getSettingsBarcodeID() {
        return settingsBarcodeID;
    }

    public void setSettingsBarcodeID(String settingsBarcodeID) {
        this.settingsBarcodeID = settingsBarcodeID;
    }

    public Integer getSettingsClearance() {
        return settingsClearance;
    }

    public void setSettingsClearance(Integer settingsClearance) {
        this.settingsClearance = settingsClearance;
    }

    public String getSettingsType() {
        return settingsType;
    }

    public void setSettingsType(String settingsType) {
        this.settingsType = settingsType;
    }

    public String getSettingsLocation() {
        return settingsLocation;
    }

    public void setSettingsLocation(String settingsLocation) {
        this.settingsLocation = settingsLocation;
    }

    public String getSettingsDescription() {
        return settingsDescription;
    }

    public void setSettingsDescription(String settingsDescription) {
        this.settingsDescription = settingsDescription;
    }
}
