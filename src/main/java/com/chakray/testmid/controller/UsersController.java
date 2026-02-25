package com.chakray.testmid.controller;

import com.chakray.testmid.model.UsersModel;
import com.chakray.testmid.service.UsersService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author luis-barrera
 */
@RestController
public class UsersController {

    @Autowired
    private UsersService usersService;

    @PostMapping("/users")
    public ResponseEntity<UsersModel> saveUser(@Valid @RequestBody UsersModel user) {

        UsersModel userSave = usersService.saveUser(user);

        userSave.setPassword(null);

        return ResponseEntity.status(HttpStatus.CREATED).body(userSave);
    }

    @GetMapping
    public ResponseEntity<List<UsersModel>> getAllUsers(@RequestParam(required = false) String sortedBy) {

        List<UsersModel> usersList = usersService.getAllUsers(sortedBy);

        for (UsersModel userList : usersList) {
            userList.setPassword(null);
        }

        return ResponseEntity.ok(usersList);
    }

    @PatchMapping("/users/{id}")
    public ResponseEntity<UsersModel> updateUser(@PathVariable Integer id, @RequestBody UsersModel user) {

        UsersModel updateUser = usersService.updateUser(id, user);

        updateUser.setPassword(null);

        return ResponseEntity.ok(updateUser);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
        usersService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/users")
    public ResponseEntity<List<UsersModel>> getUsersFilters(@RequestParam(required = false) String sortedBy, @RequestParam(required = false) String filter) {

        if (filter != null) {
            List<UsersModel> usersList = usersService.filterUsers(filter);

            for (UsersModel userList : usersList) {
                userList.setPassword(null);
            }

            return ResponseEntity.ok(usersList);

        }

        List<UsersModel> usersList = usersService.getAllUsers(sortedBy);

        for (UsersModel userList : usersList) {
            userList.setPassword(null);
        }

        return ResponseEntity.ok(usersList);
    }

}
