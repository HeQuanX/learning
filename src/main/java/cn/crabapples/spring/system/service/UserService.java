package cn.crabapples.spring.system.service;

import cn.crabapples.spring.system.entity.User;
import cn.crabapples.spring.system.form.UserForm;

import java.util.List;
import java.util.Optional;

/**
 * TODO 用户相关服务
 *
 * @author Mr.He
 * 2020/1/27 2:10
 * e-mail crabapples.cn@gmail.com
 * qq 294046317
 * pc-name 29404
 */
public interface UserService {
    /**
     * 测试方法-使用hql查询
     */
    List<User> findByHQL(String name);

    /**
     * 测试方法-使用sql查询
     */
    List<User> findBySQL(String name);

    /**
     * 添加用户
     */
    User addUser(UserForm form);

    /**
     * 修改用户
     */
    User editUser(UserForm form);

    /**
     * 删除用户
     */
    void delUser(String id);

    List<User> findByName(String name);

    /**
     * 禁用用户
     */
    void unActiveUser(String id);

    /**
     * 激活用户
     */
    void activeUser(String id);

    Optional<User> findByUsernameAndPasswordAndStatusNotAndDelFlagNot(String username, String password, int status, int delFlag);
}
