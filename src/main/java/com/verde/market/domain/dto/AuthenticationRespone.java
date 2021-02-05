package com.verde.market.domain.dto;

public class AuthenticationRespone {
    private  String jwt;

    public AuthenticationRespone(String jwt) {
        this.jwt = jwt;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
}
