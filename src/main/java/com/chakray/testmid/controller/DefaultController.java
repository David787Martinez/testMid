package com.chakray.testmid.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author luis-barrera
 */

@RestController
public class DefaultController {
    
    @Value("${version.aplicativo}")
    private String Version; 
    
    @GetMapping("/version")
    public String version(){
        return "version: "+Version;
    }
}