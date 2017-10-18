package com.example.admin.fpbackend.DataObjects;

/**
 * Created by Admin on 10/11/2017.
 */

public class HousingInfoClass {
    int id;
    String name;
    String address;
    int numBedroom;
    int numBathroom;
    String buildingManagerPhone;
    String gateCode;
    String lockCode;
    double lat;
    double lng;
    String refrence;
    String houseManager;

    public HousingInfoClass() {
    }

    public HousingInfoClass(int id, String name, String address, int numBedroom, int numBathroom, String buildingManagerPhone, String gateCode, String lockCode, double lat, double lng, String refrence, String houseManager) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.numBedroom = numBedroom;
        this.numBathroom = numBathroom;
        this.buildingManagerPhone = buildingManagerPhone;
        this.gateCode = gateCode;
        this.lockCode = lockCode;
        this.lat = lat;
        this.lng = lng;
        this.refrence = refrence;
        this.houseManager = houseManager;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getNumBedroom() {
        return numBedroom;
    }

    public void setNumBedroom(int numBedroom) {
        this.numBedroom = numBedroom;
    }

    public int getNumBathroom() {
        return numBathroom;
    }

    public void setNumBathroom(int numBathroom) {
        this.numBathroom = numBathroom;
    }

    public String getBuildingManagerPhone() {
        return buildingManagerPhone;
    }

    public void setBuildingManagerPhone(String buildingManagerPhone) {
        this.buildingManagerPhone = buildingManagerPhone;
    }

    public String getGateCode() {
        return gateCode;
    }

    public void setGateCode(String gateCode) {
        this.gateCode = gateCode;
    }

    public String getLockCode() {
        return lockCode;
    }

    public void setLockCode(String lockCode) {
        this.lockCode = lockCode;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public String getRefrence() {
        return refrence;
    }

    public void setRefrence(String refrence) {
        this.refrence = refrence;
    }

    public String getHouseManager() {
        return houseManager;
    }

    public void setHouseManager(String houseManager) {
        this.houseManager = houseManager;
    }
}
