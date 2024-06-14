package com.bucksbuddy.bucksbuddy;

import com.bucksbuddy.bucksbuddy.user.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Map;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class UserControllerTest {

    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserController userController;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
        this.objectMapper = new ObjectMapper();
    }

    @Test
    public void testLoginSuccess() throws Exception {
        UserLoginRequest request = new UserLoginRequest();
        request.setEmail("test@example.com");
        request.setPassword("password");

        User user = new User();
        user.setEmail("test@example.com");
        user.setPassword("encodedPassword");
        user.setUuid("test-uuid");

        when(userService.getUserByEmail("test@example.com")).thenReturn(Optional.of(user));
        when(passwordEncoder.matches("password", "encodedPassword")).thenReturn(true);

        mockMvc.perform(post("/users/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().string("UUID: test-uuid"));
    }

    @Test
    public void testLoginFailure() throws Exception {
        UserLoginRequest request = new UserLoginRequest();
        request.setEmail("test@example.com");
        request.setPassword("wrongPassword");

        User user = new User();
        user.setEmail("test@example.com");
        user.setPassword("encodedPassword");

        when(userService.getUserByEmail("test@example.com")).thenReturn(Optional.of(user));
        when(passwordEncoder.matches("wrongPassword", "encodedPassword")).thenReturn(false);

        mockMvc.perform(post("/users/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Invalid credentials"));
    }

    @Test
    public void testCreateUserSuccess() throws Exception {
        User user = new User();
        user.setEmail("new@example.com");
        user.setPassword("newPassword");

        User savedUser = new User();
        savedUser.setEmail("new@example.com");
        savedUser.setPassword("encodedPassword");

        when(passwordEncoder.encode("newPassword")).thenReturn("encodedPassword");
        when(userService.saveUser(any(User.class))).thenReturn(savedUser);

        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.email").value("new@example.com"))
                .andExpect(jsonPath("$.password").value("encodedPassword"));
    }

    @Test
    public void testDeleteUserSuccess() throws Exception {
        User user = new User();
        user.setUuid("test-uuid");

        when(userService.getUserByUuid("test-uuid")).thenReturn(Optional.of(user));

        mockMvc.perform(delete("/users")
                        .header("uuid", "test-uuid"))
                .andExpect(status().isNoContent());

        verify(userService, times(1)).deleteUser("test-uuid");
    }

    @Test
    public void testDeleteUserNotFound() throws Exception {
        when(userService.getUserByUuid("test-uuid")).thenReturn(Optional.empty());

        mockMvc.perform(delete("/users")
                        .header("uuid", "test-uuid"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testUpdateUserPasswordSuccess() throws Exception {
        User user = new User();
        user.setUuid("test-uuid");
        user.setPassword("oldPassword");

        when(userService.getUserByUuid("test-uuid")).thenReturn(Optional.of(user));
        when(passwordEncoder.encode("newPassword")).thenReturn("encodedNewPassword");
        when(userService.updateUserPassword("test-uuid", "encodedNewPassword")).thenReturn(Optional.of(user));

        mockMvc.perform(patch("/users/password")
                        .header("uuid", "test-uuid")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(Map.of("newPassword", "newPassword"))))
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdateUserPasswordBadRequest() throws Exception {
        mockMvc.perform(patch("/users/password")
                        .header("uuid", "test-uuid")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(Map.of("newPassword", ""))))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testUpdateUserPasswordNotFound() throws Exception {
        when(userService.updateUserPassword("test-uuid", "encodedNewPassword")).thenReturn(Optional.empty());

        mockMvc.perform(patch("/users/password")
                        .header("uuid", "test-uuid")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(Map.of("newPassword", "newPassword"))))
                .andExpect(status().isNotFound());
    }
}
