package com.hcl.cloud.modernapp.model;

public class UIResponse {
    private String statusDescription;
    private Integer statusCode;
    private String token;
    public UIResponse() {

    }
    public String getStatusDescription() {
        return statusDescription;
    }

    public void setStatusDescription(String statusDescription) {
        this.statusDescription = statusDescription;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getToken() {return token;}

    public void setToken(String token) {
        this.token = token;
    }
}
