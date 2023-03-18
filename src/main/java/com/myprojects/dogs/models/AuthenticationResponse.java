package com.myprojects.dogs.models;

import java.io.Serializable;

public class AuthenticationResponse implements Serializable {
    private String jwt;

    public AuthenticationResponse(String jwt){
        this.jwt=jwt;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
}
