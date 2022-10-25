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

import java.util.List;

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

    /**
     * h2数据库可能会报没有id的异常，需要自定义BaseMapper
     *
     * @see tk.mybatis.mapper.common.BaseMapper
     */
    @PostMapping("/insertUser")
    public void insertUser(@RequestBody User user) {
        userMapper.insertSelective(user);
    }

    /**
     * 批量插入
     *
     * @see tk.mybatis.mapper.custom.insert.provider.InsertBatchProvider
     */
    @PostMapping("/insertBatch")
    public List<User> insertBatch(@RequestBody List<User> userList) {
        userMapper.insertBatch(userList);
        return userList;
    }
}
