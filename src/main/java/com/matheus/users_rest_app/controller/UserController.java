package com.matheus.users_rest_app.controller;

import com.matheus.users_rest_app.models.Users;
import com.matheus.users_rest_app.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Operation(summary = "Recupera a lista de usuários cadastrados")
    @GetMapping("/getAllUsers")
    public List<Users> getAllUsers() {
        return userService.findAll();
    }

    @Operation(summary = "Recupera um usuário pelo ID")
    @GetMapping("/getUserById/{id}")
    public ResponseEntity<Users> getUserById(@PathVariable Long id) {
        systemValidation(id);
        return userService.findById(id);
    }

    @Operation(summary = "Insere um usuário")
    @PostMapping("/createUser")
    public ResponseEntity<Users> createUser(@RequestBody Users user) {
        systemValidation(user.getId());
        return userService.save(user);
    }

    @Operation(summary = "Altera um usuário já existente")
    @PutMapping("/updateUser/{id}")
    public ResponseEntity<Users> updateUser(@PathVariable Long id, @RequestBody Users user) {
        systemValidation(id);
        return userService.update(id, user);
    }

    @Operation(summary = "Deleta um usuário")
    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        systemValidation(id);
        return userService.delete(id);
    }

    public void systemValidation(Long id){

        if ("".equalsIgnoreCase(id.toString()) || id == 0){
            throw new RuntimeException("ID inválido");
        }
    }
}
