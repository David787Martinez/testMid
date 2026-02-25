package com.chakray.testmid.response;

import java.io.Serializable;

/**
 *
 * @author luis-barrera
 */
public class MessageResponse implements Serializable{
    
    private String token;

    public MessageResponse() {
    }

    public String getMessage() {
        return token;
    }

    public void setMessage(String token) {
        this.token = token;
    }
    
    
}
