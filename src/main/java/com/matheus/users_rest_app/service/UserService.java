package com.matheus.users_rest_app.service;

import com.matheus.users_rest_app.models.Users;
import com.matheus.users_rest_app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<Users> findAll() {
        return userRepository.findAll();
    }

    public ResponseEntity<Users> findById(Long id) {

        System.out.println("Buscando usuário por ID. ID: " + id);

        try {

            Optional<Users> user = userRepository.findById(id);

            return user.map(ResponseEntity::ok)
                    .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    public ResponseEntity<Users> save(Users user) {

        System.out.println("Criando novo usuário. ID: " + user.getId());
        Optional<Users> existingUser = userRepository.findById(user.getId());

        try {

            if(existingUser.isPresent()){
                System.out.println("O usuário não pode ser criando. ID já ultilizado: " + user.getId());
                throw new RuntimeException("ID já ultilizado");

            } else {
                Users savedUser = userRepository.save(user);
                System.out.println("Usuário criado com sucesso!. ID: " + user.getId());
                return ResponseEntity.status(HttpStatus.OK).body(savedUser);
            }

        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    public ResponseEntity<Users> update(Long id, Users user) {

        System.out.println("Alterando um usuário. ID: " + id);
        Optional<Users> existingUser = userRepository.findById(id);

        try {

            if (existingUser.isPresent()) {

                user.setId(existingUser.get().getId());
                Users updatedUser = userRepository.save(user);
                System.out.println("Usuário alterado com sucesso!. ID: " + id);
                return ResponseEntity.status(HttpStatus.OK).body(updatedUser);

            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    public ResponseEntity<String> delete(Long id) {

        System.out.println("Deletando um usuário. ID: " + id);
        Optional<Users> existingUser = userRepository.findById(id);

        try {

            if (existingUser.isPresent()) {
                userRepository.deleteById(id);
                return ResponseEntity.status(HttpStatus.OK).body("Usuário com id: " + id + " - Deletado com sucesso!");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário com id: " + id + " - Não encontrado no sistema!");
            }

        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
