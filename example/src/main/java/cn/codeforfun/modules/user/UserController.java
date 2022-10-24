package cn.codeforfun.modules.user;

import cn.codeforfun.generator.mapper.UserMapper;
import cn.codeforfun.generator.model.User;
import cn.codeforfun.utils.Page;
import com.github.pagehelper.PageHelper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@AllArgsConstructor
public class UserController {
    UserMapper userMapper;

    @PostMapping("/userList")
    public Page<User> userList(@RequestBody Page<User> page) {
        PageHelper.startPage(page.getPageNo(), page.getPageSize());
        return Page.from(userMapper.selectAll());
    }
}
