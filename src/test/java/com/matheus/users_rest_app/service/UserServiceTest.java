package com.matheus.users_rest_app.service;

import com.matheus.users_rest_app.models.Users;
import com.matheus.users_rest_app.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void testFindAll() {
        List<Users> users = List.of(new Users(1L, "Matheus"), new Users(1L, "Matheus"));
        when(userRepository.findAll()).thenReturn(users);

        List<Users> result = userService.findAll();

        assertEquals(2, result.size());
        assertEquals("Matheus", result.get(0).getFullName());
    }

    @Test
    void testFindByIdUserExists() {
        Users user = new Users(1L, "Matheus");
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        ResponseEntity<Users> response = userService.findById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Matheus", response.getBody().getFullName());
    }

    @Test
    void testFindByIdUserNotFound() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        ResponseEntity<Users> response = userService.findById(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void testSaveUserAlreadyExists() {
        Users user = new Users(1L, "Matheus");
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        ResponseEntity<Users> response = userService.save(user);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    void testSaveNewUser() {
        Users user = new Users(1L, "Matheus");
        when(userRepository.findById(1L)).thenReturn(Optional.empty());
        when(userRepository.save(user)).thenReturn(user);

        ResponseEntity<Users> response = userService.save(user);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Matheus", response.getBody().getFullName());
    }

    @Test
    void testUpdateUserExists() {
        Users user = new Users(1L, "Matheus");
        Users updatedUser = new Users(1L, "Mateus");

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(userRepository.save(updatedUser)).thenReturn(updatedUser);

        ResponseEntity<Users> response = userService.update(1L, updatedUser);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Mateus", response.getBody().getFullName());
    }

    @Test
    void testUpdateUserNotFound() {
        Users user = new Users(1L, "Matheus");
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        ResponseEntity<Users> response = userService.update(1L, user);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testDeleteUserExists() {
        Users user = new Users(1L, "Matheus");
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        ResponseEntity<String> response = userService.delete(1L);

        verify(userRepository, times(1)).deleteById(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Usuário com id: 1 - Deletado com sucesso!", response.getBody());
    }

    @Test
    void testDeleteUserNotFound() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        ResponseEntity<String> response = userService.delete(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Usuário com id: 1 - Não encontrado no sistema!", response.getBody());
    }
}

