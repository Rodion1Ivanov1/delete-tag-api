package stack.overflow.backend.controller;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import stack.overflow.backend.IntegrationTestContext;
import stack.overflow.backend.repository.TagRepository;


import javax.persistence.EntityNotFoundException;

import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ModeratorTagRestControllerTest extends IntegrationTestContext {

    @Autowired
    TagRepository tagRepository;

    @Sql(executionPhase = BEFORE_TEST_METHOD, value = "/sql/ModeratorTagRestControllerTest/deleteTagTest/before.sql")
    @Sql(executionPhase = AFTER_TEST_METHOD, value = "/sql/ModeratorTagRestControllerTest/deleteTagTest/after.sql")
    @Test
    public void deleteTagsTest() throws Exception {
        Long id = 1L;
        if (tagRepository.findById(id).isEmpty()) {
            Assert.fail("Тэга с таким Id нет");
        }
        String token = testUtil.getToken("user1", "password");
        mockMvc.perform(delete("/api/v1/moderator/tags/{tagId}", id)
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk());
        if (tagRepository.findById(id).isPresent()) {
            Assert.fail("Тэг не удалён");
        }
    }

}
