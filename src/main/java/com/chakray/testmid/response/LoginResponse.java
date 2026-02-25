package com.chakray.testmid.response;

import java.io.Serializable;

/**
 *
 * @author luis-barrera
 */
public class LoginResponse implements Serializable{
    
    private String token;

    public LoginResponse(String token) {
        this.token = token;
    }

    
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
    
}
