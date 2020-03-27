package cn.crabapples.spring.system.service.impl;

import cn.crabapples.spring.common.ApplicationException;
import cn.crabapples.spring.system.dao.UserRepository;
import cn.crabapples.spring.system.entity.User;
import cn.crabapples.spring.system.form.UserForm;
import cn.crabapples.spring.system.service.UserService;
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
    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User addUser(UserForm form) {
        return userRepository.save(form.toEntity());
    }

    @Override
    public User editUser(UserForm form) {
        User user = userRepository.findById(form.getId()).orElse(null);
        if (user != null) {
            return userRepository.save(form.toEntity());
        }
        throw new ApplicationException("用户不存在");
    }

    @Override
    @Transactional
    public void delUser(String id) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            throw new ApplicationException("用户不存在");
        }
        userRepository.delUser(id);
    }

    @Override
    public List<User> findByName(String name) {
        return userRepository.findByName(name);
    }

    @Override
    public List<User> findByHQL(String name) {
        return userRepository.findByHQL(name);
    }

    @Override
    public List<User> findBySQL(String name) {
        return userRepository.findBySQL(name);
    }

    @Override
    @Transactional
    public void unActiveUser(String id) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            throw new ApplicationException("用户不存在");
        }
        userRepository.unActiveUser(id);
    }

    @Override
    @Transactional
    public void activeUser(String id) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            throw new ApplicationException("用户不存在");
        }
        userRepository.activeUser(id);
    }

    @Override
    public Optional<User> findByUsernameAndPasswordAndStatusNotAndDelFlagNot(String username, String password, int status, int delFlag) {
        return userRepository.findByUsernameAndPasswordAndStatusNotAndDelFlagNot(username, password, status, delFlag);
    }
}
