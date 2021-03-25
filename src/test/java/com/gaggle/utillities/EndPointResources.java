package com.gaggle.utillities;

public enum EndPointResources {
    AddPlace("maps/api/place/add/json"),
    DeletePlace("maps/api/place/delete/json"),
    GetPlace("maps/api/place/get/json");
    private String endPoint;

  EndPointResources(String endPoint){
        this.endPoint=endPoint;
    }

    public String getEndPoint() {
        return endPoint;
    }
}
