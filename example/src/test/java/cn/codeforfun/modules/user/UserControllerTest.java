package cn.codeforfun.modules.user;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
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
                .andExpect(jsonPath("$.totalCount").value(2))
                .andExpect(jsonPath("$.result").isArray())
                .andExpect(jsonPath("$.result[1].id").value(2))
                .andExpect(jsonPath("$.result[1].name").value("李四"))
                .andExpect(jsonPath("$.result[1].email").value("lisi@admin.com"))
                .andDo(print())
        ;
    }

    @Test
    void insertUser() throws Exception {
        JSONObject param = JSONUtil.createObj()
                .set("name", "王五").set("email", "wangwu@admin.com");
        mockMvc.perform(post("/insertUser").contentType(MediaType.APPLICATION_JSON).content(param.toString()))
                .andExpect(status().isOk())
                .andDo(print())
        ;
    }

    @Test
    void insertBatch() throws Exception {
        JSONArray param = JSONUtil.createArray();
        param.add(JSONUtil.createObj().set("name", "王五").set("email", "wangwu@admin.com"));
        param.add(JSONUtil.createObj().set("name", "赵六").set("email", "zhaoliu@admin.com"));
        mockMvc.perform(post("/insertBatch").contentType(MediaType.APPLICATION_JSON).content(param.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].id").value(3))
                .andExpect(jsonPath("$[1].id").value(4))
                .andDo(print())
        ;

        mockMvc.perform(post("/userList")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JSONUtil.createObj().toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalCount").value(4))
                .andExpect(jsonPath("$.result").isArray())
                .andExpect(jsonPath("$.result[2].id").value(3))
                .andExpect(jsonPath("$.result[2].name").value("王五"))
                .andExpect(jsonPath("$.result[2].email").value("wangwu@admin.com"))
                .andExpect(jsonPath("$.result[3].id").value(4))
                .andExpect(jsonPath("$.result[3].name").value("赵六"))
                .andExpect(jsonPath("$.result[3].email").value("zhaoliu@admin.com"))
                .andDo(print())
        ;
    }
}