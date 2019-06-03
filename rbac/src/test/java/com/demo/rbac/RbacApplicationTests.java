package com.demo.rbac;

import com.demo.rbac.dao.common.UserDao;
import com.demo.rbac.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RbacApplicationTests {

    @Autowired
    UserDao dao;

    @Test
    public void contextLoads() {
        Example example = new Example(User.class);
        example.setForUpdate(true);
        example.createCriteria().andLike("avator", "%png%");
        example.orderBy("uid").desc();
        List<User> users =dao.selectByExample(example);
        for (User user : users) {
            System.out.println(user);
        }
    }

}
