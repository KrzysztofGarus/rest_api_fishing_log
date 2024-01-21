package pl.someday.rest_api_fishing_log.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import pl.someday.rest_api_fishing_log.dto.AuthDTO.JWTAuthenticationResponse;
import pl.someday.rest_api_fishing_log.dto.AuthDTO.SignUpRequest;
import pl.someday.rest_api_fishing_log.dto.AuthDTO.SingInRequest;
import pl.someday.rest_api_fishing_log.service.AuthenticationService;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthenticationService authenticationService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void signUpTest() throws Exception {
        SignUpRequest signUpRequest = new SignUpRequest("user", "user", "user@aaaaa", "useruser");

        doNothing().when(authenticationService).signUp(signUpRequest);

        mockMvc.perform(post("/api/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(signUpRequest)))
                .andExpect(status().isCreated())
                .andExpect(content().string("User registered successfully"));
    }

    @Test
    public void signInTest() throws Exception {
        SingInRequest signInRequest = new SingInRequest("user@aaaaa", "useruser");

        JWTAuthenticationResponse expectedResponse = new JWTAuthenticationResponse("token");

        when(authenticationService.signIn(signInRequest)).thenReturn(expectedResponse);

        mockMvc.perform(post("/api/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(signInRequest)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(expectedResponse)));
    }
}
