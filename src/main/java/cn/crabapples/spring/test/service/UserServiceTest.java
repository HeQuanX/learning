package cn.crabapples.spring.test.service;

import cn.crabapples.spring.system.entity.SysUser;

import java.util.List;

/**
 * TODO 用户相关服务
 *
 * @author Mr.He
 * 2020/3/6 0:01
 * e-mail crabapples.cn@gmail.com
 * qq 294046317
 * pc-name 29404
 */
public interface UserServiceTest {
    /**
     * 测试方法-使用hql查询
     */
    List<SysUser> findByHQL(String name);

    /**
     * 测试方法-使用sql查询
     */
    List<SysUser> findBySQL(String name);
}
