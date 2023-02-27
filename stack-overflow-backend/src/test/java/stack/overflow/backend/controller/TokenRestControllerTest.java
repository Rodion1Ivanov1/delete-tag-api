package stack.overflow.backend.controller;

import org.hamcrest.Matchers;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import stack.overflow.backend.IntegrationTestContext;
import stack.overflow.backend.model.dto.jwt.JwtRequestDto;

import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class TokenRestControllerTest extends IntegrationTestContext {

    @Sql(executionPhase = BEFORE_TEST_METHOD, value = "/sql/TokenRestControllerTest/getTokenTest/before.sql")
    @Sql(executionPhase = AFTER_TEST_METHOD, value = "/sql/TokenRestControllerTest/getTokenTest/after.sql")
    @Test
    public void getTokenTest() throws Exception {
        JwtRequestDto dto = new JwtRequestDto("user1", "password");
        mockMvc.perform(post("/api/v1/token")
                        .content(objectMapper.writeValueAsString(dto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data", Matchers.notNullValue()));
    }

    @Sql(executionPhase = BEFORE_TEST_METHOD, value = "/sql/TokenRestControllerTest/isTokenExpiredTest/before.sql")
    @Sql(executionPhase = AFTER_TEST_METHOD, value = "/sql/TokenRestControllerTest/isTokenExpiredTest/after.sql")
    @Test
    public void isTokenExpiredTest() throws Exception {
        String token = testUtil.getToken("user1", "password");
        mockMvc.perform(get("/api/v1/token/is-expired")
                        .param("token", token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data", Is.is(false)));
    }
}
