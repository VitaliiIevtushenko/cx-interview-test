package com.transportexchangegroup.fm.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.transportexchangegroup.fm.auth.AuthJwtToken;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
@ActiveProfiles("test")
public abstract class BaseControllerTest {
    static final String JWT_TOKEN = "jwt_token";

    @Autowired
    protected MockMvc mvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @Autowired
    protected ApplicationContext applicationContext;

    AuthJwtToken getToken(int loggedInCompanyId) {
        return AuthJwtToken.builder()
                .companyId(loggedInCompanyId)
                .build();
    }

    AuthJwtToken getToken(int loggedInCompanyId, int loggedInUserId) {
        AuthJwtToken token = getToken(loggedInCompanyId);
        token.setUserId(loggedInUserId);
        return token;
    }

    String getErrorResponseAsString(String message) throws  Exception {
        return objectMapper.writeValueAsString(new ServerResponseDto(message));
    }

    @Getter
    @AllArgsConstructor
    private class ServerResponseDto {
        private String message;
    }
}