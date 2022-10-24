package cn.codeforfun.modules.user;

import cn.hutool.json.JSONUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import javax.annotation.Resource;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("unit-test-user")
@AutoConfigureMockMvc
class UserControllerTest {
    @Resource
    MockMvc mockMvc;

    @Test
    void userList() throws Exception {
        mockMvc.perform(post("/userList")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JSONUtil.createObj().toString()))
                .andExpect(status().isOk())
                .andDo(print())
        ;
    }
}