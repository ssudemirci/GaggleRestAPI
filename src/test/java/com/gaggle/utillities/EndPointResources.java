package com.gaggle.utillities;

public enum EndPointResources {
    AddPlace(""),
    DeletePlace(""),
    GetPlace("");
    private String endPoint;

  EndPointResources(String endPoint){
        this.endPoint=endPoint;
    }

    public String getEndPoint() {
        return endPoint;
    }
}
