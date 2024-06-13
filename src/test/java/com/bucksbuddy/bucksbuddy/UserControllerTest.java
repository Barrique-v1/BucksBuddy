package com.bucksbuddy.bucksbuddy;

import com.bucksbuddy.bucksbuddy.user.User;
import com.bucksbuddy.bucksbuddy.user.UserController;
import com.bucksbuddy.bucksbuddy.user.UserLoginRequest;
import com.bucksbuddy.bucksbuddy.user.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testLoginSuccess() throws Exception {
        User user = new User("test@example.com", "password");
        when(userService.getUserByEmail("test@example.com")).thenReturn(Optional.of(user));
        when(userService.validate("test@example.com", "password")).thenReturn(true);

        UserLoginRequest request = new UserLoginRequest();
        request.setEmail("test@example.com");
        request.setPassword("password");

        mockMvc.perform(post("/users/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().string("UUID: " + user.getUuid()));
    }

    @Test
    public void testLoginFailure() throws Exception {
        when(userService.getUserByEmail("test@example.com")).thenReturn(Optional.empty());

        UserLoginRequest request = new UserLoginRequest();
        request.setEmail("test@example.com");
        request.setPassword("password");

        mockMvc.perform(post("/users/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Invalid credentials"));
    }

    @Test
    public void testCreateUser() throws Exception {
        User user = new User("test@example.com", "password");
        when(userService.saveUser(any(User.class))).thenReturn(user);

        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.email").value("test@example.com"));
    }

    @Test
    public void testDeleteUserSuccess() throws Exception {
        User user = new User("test@example.com", "password");
        when(userService.getUserByUuid(anyString())).thenReturn(Optional.of(user));

        mockMvc.perform(delete("/users")
                        .header("uuid", user.getUuid()))
                .andExpect(status().isNoContent());

        verify(userService).deleteUser(user.getUuid());
    }

    @Test
    public void testDeleteUserNotFound() throws Exception {
        when(userService.getUserByUuid(anyString())).thenReturn(Optional.empty());

        mockMvc.perform(delete("/users")
                        .header("uuid", "invalid-uuid"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testUpdateUserPasswordSuccess() throws Exception {
        User user = new User("test@example.com", "password");
        when(userService.updateUserPassword(anyString(), anyString())).thenReturn(Optional.of(user));

        Map<String, String> payload = new HashMap<>();
        payload.put("newPassword", "newPassword");

        mockMvc.perform(patch("/users/password")
                        .header("uuid", user.getUuid())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(payload)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("test@example.com"));
    }

    @Test
    public void testUpdateUserPasswordBadRequest() throws Exception {
        Map<String, String> payload = new HashMap<>();

        mockMvc.perform(patch("/users/password")
                        .header("uuid", "some-uuid")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(payload)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testUpdateUserPasswordNotFound() throws Exception {
        when(userService.updateUserPassword(anyString(), anyString())).thenReturn(Optional.empty());

        Map<String, String> payload = new HashMap<>();
        payload.put("newPassword", "newPassword");

        mockMvc.perform(patch("/users/password")
                        .header("uuid", "invalid-uuid")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(payload)))
                .andExpect(status().isNotFound());
    }
}
