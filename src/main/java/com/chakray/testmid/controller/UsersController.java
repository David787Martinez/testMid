package com.chakray.testmid.controller;

import com.chakray.testmid.model.UsersModel;
import com.chakray.testmid.response.MessageResponse;
import com.chakray.testmid.service.UsersService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;
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

    @Operation(summary = "Ingresa el token para poder agregar un usuario.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se crea un usuario de menera exitosa.",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = MessageResponse.class)))),
            @ApiResponse(responseCode = "403", description = "No tienes acceso al endpoint.",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = MessageResponse.class))))
    })
//    @SecurityRequirement(name = "BearerToken")
    @PostMapping("/users")
    public ResponseEntity<UsersModel> saveUser(@Valid @RequestBody UsersModel user) {

        UsersModel userSave = usersService.saveUser(user);

        userSave.setPassword(null);

        return ResponseEntity.status(HttpStatus.CREATED).body(userSave);
    }

    @Operation(summary = "Ingresa el token para obtener una lista ordenada.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista ordenada por la clave que ingreses.",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = MessageResponse.class)))),
            @ApiResponse(responseCode = "403", description = "No tienes acceso al endpoint.",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = MessageResponse.class))))
    })
    @SecurityRequirement(name = "BearerToken")
    @GetMapping
    public ResponseEntity<List<UsersModel>> getAllUsers(@RequestParam(required = false) String sortedBy) {

        List<UsersModel> usersList = usersService.getAllUsers(sortedBy);

        for (UsersModel userList : usersList) {
            userList.setPassword(null);
        }

        return ResponseEntity.ok(usersList);
    }

    @Operation(summary = "Ingresa el token para actualizar un registro por ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se actualiza el registro correctamente.",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = MessageResponse.class)))),
            @ApiResponse(responseCode = "403", description = "No tienes acceso al endpoint.",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = MessageResponse.class))))
    })
    @SecurityRequirement(name = "BearerToken")
    @PatchMapping("/users/{id}")
    public ResponseEntity<UsersModel> updateUser(@PathVariable Integer id, @RequestBody UsersModel user) {

        UsersModel updateUser = usersService.updateUser(id, user);

        updateUser.setPassword(null);

        return ResponseEntity.ok(updateUser);
    }

    @Operation(summary = "Ingresa el token para eliminar un registro por ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se elimino correctamente el registro.",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = MessageResponse.class)))),
            @ApiResponse(responseCode = "403", description = "No tienes acceso al endpoint.",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = MessageResponse.class))))
    })
    @SecurityRequirement(name = "BearerToken")
    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
        usersService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Ingresa el token para obtener una lista con los filtros que agregues por clave.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista obtenida por los filtros aplicados.",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = MessageResponse.class)))),
            @ApiResponse(responseCode = "403", description = "No tienes acceso al endpoint.",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = MessageResponse.class))))
    })
    @SecurityRequirement(name = "BearerToken")
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
