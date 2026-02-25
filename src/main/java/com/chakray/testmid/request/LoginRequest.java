package com.chakray.testmid.request;

/**
 *
 * @author luis-barrera
 */
public class LoginRequest {
    
    private String taxId;
    private String password;

    public LoginRequest() {
    }

    public String getTaxId() {
        return taxId;
    }

    public void setTaxId(String taxId) {
        this.taxId = taxId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
}
