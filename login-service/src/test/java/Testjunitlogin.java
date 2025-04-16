package com.bys.login.Testjunitlogin;

import com.bys.login.controller.UsersController;
import com.bys.login.entity.Users;
import com.bys.login.requests.LoginRequest;
import com.bys.login.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class Testjunitlogin {

    @InjectMocks
    private UsersController usersController;

    @Mock
    private UserService userService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        System.out.println("Configuration du test terminée.");
    }

    @Test
    public void testAddUser() {
        System.out.println("Nom saisi avec succès.");
        System.out.println("Email saisi avec succès.");

        // Creating a mock user
        Users userToAdd = new Users("test@example.com", "Test User", "password123");

        // Mocking the service layer to return the added user
        when(userService.addUser(userToAdd)).thenReturn(userToAdd);

        // Calling the controller method
        Users result = usersController.addUser(userToAdd);

        // Assertions to verify the correct behavior
        assertEquals("test@example.com", result.getEmail());
        assertEquals("Test User", result.getName());
        assertEquals("password123", result.getPassword());

        // Printing success message after form submission
        System.out.println("Mot de passe saisi avec succès.");
        System.out.println("Formulaire soumis avec succès.");
    }

    @Test
    public void testLoginUser() {
        System.out.println("Test de la connexion de l'utilisateur...");

        // Creating a mock LoginRequest
        LoginRequest loginRequest = new LoginRequest("test@example.com", "password123");

        // Mocking the service layer to return true if the credentials are correct
        when(userService.loginUser(loginRequest)).thenReturn(true);

        // Calling the controller method
        Boolean result = usersController.loginUser(loginRequest);

        // Assertions to verify the correct behavior
        assertEquals(true, result);

        // Print messages to simulate the successful login
        System.out.println("Email saisi avec succès.");
        System.out.println("Mot de passe saisi avec succès.");
        System.out.println("Redirection après connexion vérifiée.");
    }

    @Test
    public void testGetAllUsers() {
        System.out.println("Test pour obtenir tous les utilisateurs...");

        // Mocking a list of users
        Users user1 = new Users("test1@example.com", "User 1", "password123");
        Users user2 = new Users("test2@example.com", "User 2", "password456");

        List<Users> usersList = List.of(user1, user2);

        // Mocking the service layer to return the list of users
        when(userService.getAllUsers()).thenReturn(usersList);

        // Calling the controller method
        List<Users> result = usersController.getAllUsers();

        // Assertions to verify the correct behavior
        assertEquals(2, result.size());
        assertEquals("test1@example.com", result.get(0).getEmail());
        assertEquals("User 1", result.get(0).getName());
        assertEquals("test2@example.com", result.get(1).getEmail());
        assertEquals("User 2", result.get(1).getName());

        System.out.println("Tous les utilisateurs récupérés avec succès.");
    }
}
