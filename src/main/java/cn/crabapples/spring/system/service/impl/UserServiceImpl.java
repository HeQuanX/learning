package cn.crabapples.spring.system.service.impl;

import cn.crabapples.spring.system.common.ApplicationException;
import cn.crabapples.spring.system.dao.UserRepository;
import cn.crabapples.spring.system.entity.SysUser;
import cn.crabapples.spring.system.form.UserForm;
import cn.crabapples.spring.system.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * TODO 用户相关服务实现类
 *
 * @author Mr.He
 * 2020/1/27 2:10
 * e-mail crabapples.cn@gmail.com
 * qq 294046317
 * pc-name 29404
 */
@Service
public class UserServiceImpl implements UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private cn.crabapples.spring.system.dao.UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * 根据 [用户名] 查询用户
     * @param username 用户名
     * @return 查询到的用户
     */
    @Override
    public Optional<cn.crabapples.spring.system.entity.SysUser> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public cn.crabapples.spring.system.entity.SysUser addUser(cn.crabapples.spring.system.form.UserForm form) {
        return userRepository.save(form.toEntity());
    }

    @Override
    public cn.crabapples.spring.system.entity.SysUser editUser(UserForm form) {
        cn.crabapples.spring.system.entity.SysUser user = userRepository.findById(form.getId()).orElse(null);
        if (user != null) {
            return userRepository.save(form.toEntity());
        }
        throw new cn.crabapples.spring.system.common.ApplicationException("用户不存在");
    }

    @Override
    @Transactional
    public void delUser(String id) {
        cn.crabapples.spring.system.entity.SysUser user = userRepository.findById(id).orElse(null);
        if (user == null) {
            throw new cn.crabapples.spring.system.common.ApplicationException("用户不存在");
        }
        userRepository.delUser(id);
    }

    @Override
    public List<cn.crabapples.spring.system.entity.SysUser> findByName(String name) {
        return userRepository.findByName(name);
    }

    @Override
    @Transactional
    public void unActiveUser(String id) {
        cn.crabapples.spring.system.entity.SysUser user = userRepository.findById(id).orElse(null);
        if (user == null) {
            throw new cn.crabapples.spring.system.common.ApplicationException("用户不存在");
        }
        userRepository.unActiveUser(id);
    }

    @Override
    @Transactional
    public void activeUser(String id) {
        cn.crabapples.spring.system.entity.SysUser user = userRepository.findById(id).orElse(null);
        if (user == null) {
            throw new ApplicationException("用户不存在");
        }
        userRepository.activeUser(id);
    }

    @Override
    public List<cn.crabapples.spring.system.entity.SysUser> findAll() {
        logger.info("开始获取所有用户");
        return userRepository.findAll();
    }

    /**
     * 根据[用户名] [密码] [状态] [删除标记] 查询用户
     * @param username 用户名
     * @param password 密码
     * @param status 状态
     * @param delFlag 删除标记
     * @return 查询到的用户
     */
    @Override
    public Optional<SysUser> findByUsernameAndPasswordAndStatusNotAndDelFlagNot(String username, String password, int status, int delFlag) {
        return userRepository.findByUsernameAndPasswordAndStatusNotAndDelFlagNot(username, password, status, delFlag);
    }


}
