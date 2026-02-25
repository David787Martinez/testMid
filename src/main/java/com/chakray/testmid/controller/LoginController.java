package com.chakray.testmid.controller;

import com.chakray.testmid.security.JwtHelper;
import com.chakray.testmid.model.UsersModel;
import com.chakray.testmid.request.LoginRequest;
import com.chakray.testmid.response.LoginResponse;
import com.chakray.testmid.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author luis-barrera
 */
@RestController
public class LoginController {

    @Autowired
    private UsersService usersService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest login) {

        UsersModel user = usersService.login(login.getTaxId(), login.getPassword());

        String jwtToken = JwtHelper.generateToken(user.getName());

        return ResponseEntity.ok(new LoginResponse(jwtToken));
    }
}
