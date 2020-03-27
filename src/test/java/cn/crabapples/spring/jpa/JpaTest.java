package cn.crabapples.spring.jpa;

import cn.crabapples.spring.system.entity.User;
import cn.crabapples.spring.system.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * TODO
 *
 * @author Mr.He
 * 2020/1/27 14:32
 * e-mail crabapples.cn@gmail.com
 * qq 294046317
 * pc-name 29404
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("dev")
public class JpaTest {
    @Autowired
    UserService userService;
    @Test
    public void jpaTest() {
        List<User> sql = userService.findBySQL("kitty");
        List<User> hql = userService.findByHQL("kitty");
        sql.forEach(System.out::println);
        System.out.println("------------分割线-------------");
        hql.forEach(System.out::println);
    }
}

