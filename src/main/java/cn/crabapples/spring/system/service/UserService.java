package cn.crabapples.spring.system.service;

import cn.crabapples.spring.system.entity.SysUser;
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
     * 根据 [用户名] 查询用户
     * @param username 用户名
     * @return 查询到的用户
     */
    Optional<SysUser> findByUsername(String username);

    /**
     * 添加用户
     */
    SysUser addUser(UserForm form);

    /**
     * 修改用户
     */
    SysUser editUser(UserForm form);

    /**
     * 删除用户
     */
    void delUser(String id);

    /**
     * 根据 [姓名] 查找用户
     * @param name 姓名
     * @return 查找到的用户列表
     */
    List<SysUser> findByName(String name);

    /**
     * 禁用用户
     */
    void unActiveUser(String id);

    /**
     * 激活用户
     */
    void activeUser(String id);

    /**
     * 根据 [用户名] [密码] [状态] [删除标记] 查询用户
     * @param username 用户名
     * @param password 密码
     * @param status 状态
     * @param delFlag 删除标记
     * @return 查询到的用户
     */
    Optional<SysUser> findByUsernameAndPasswordAndStatusNotAndDelFlagNot(String username, String password, int status, int delFlag);

    List<SysUser> findAll();

}
