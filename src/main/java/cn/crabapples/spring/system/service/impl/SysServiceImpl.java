package cn.crabapples.spring.system.service.impl;

import cn.crabapples.spring.common.ApplicationException;
import cn.crabapples.spring.common.config.ApplicationConfigure;
import cn.crabapples.spring.common.utils.AesUtils;
import cn.crabapples.spring.system.dao.SysRepository;
import cn.crabapples.spring.system.entity.SysMenu;
import cn.crabapples.spring.system.entity.User;
import cn.crabapples.spring.system.form.UserForm;
import cn.crabapples.spring.system.service.SysService;
import cn.crabapples.spring.system.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * TODO 系统相关服务实现类
 *
 * @author Mr.He
 * 2020/1/28 23:23
 * e-mail crabapples.cn@gmail.com
 * qq 294046317
 * pc-name 29404
 */
@Service
//@CacheConfig(cacheNames = "user:")
public class SysServiceImpl implements SysService {

    private static final Logger logger = LoggerFactory.getLogger(SysServiceImpl.class);
    private String aesKey;
    private String redisPrefix;
    private Long tokenCacheTime;

    private final UserService userService;

    private final SysRepository sysRepository;

    private final RedisTemplate<String,Object> redisTemplate;

    public SysServiceImpl(ApplicationConfigure applicationConfigure,
                          UserService userService,
                          SysRepository sysRepository,
                          RedisTemplate<String,Object> redisTemplate) {
        this.userService = userService;
        this.sysRepository = sysRepository;
        this.redisTemplate = redisTemplate;
        this.aesKey = applicationConfigure.AES_KEY;
        this.redisPrefix = applicationConfigure.REDIS_PREFIX;
        this.tokenCacheTime = applicationConfigure.TOKEN_CACHE_TIME;
    }

    /**
     * Cacheable
     * key: redis中key的值
     * value: redis中key的前缀
     * 例:
     * key::value:tom
     * userLogin::${#p0.username}
     * @return
     */
//    @Cacheable(value = "login:token", key = "#p0.username")
    @Override
    public Map<String, String> login(UserForm form) {
        try {
            String username = form.getUsername();
            String password = form.getPassword();
            for (int i = 0; i < 100; i++) {
                password = DigestUtils.md5Hex(password);
            }
            password = AesUtils.doFinal(aesKey, password, Cipher.ENCRYPT_MODE);
            logger.info("开始登录->用户名:[{}],密码:[{}]", username, password);
            User user = userService.findByUsernameAndPasswordAndStatusNotAndDelFlagNot(username, password, 1, 1).orElse(null);
            if (user == null) {
                throw new ApplicationException("用户名或密码错");
            }
            String token = DigestUtils.md5Hex(user.getId() + System.currentTimeMillis()).toUpperCase();
            String tokenKey = redisPrefix + ":loginToken:" + user.getId();
            Map<String,String> tokenInfo = new HashMap<>(2);
            tokenInfo.put("token",token);
            Boolean aBoolean = redisTemplate.opsForValue().setIfAbsent(tokenKey, tokenInfo, tokenCacheTime, TimeUnit.MINUTES);
            Long time = redisTemplate.getExpire(tokenKey);
            logger.info("登录成功->token:[{}],token过期剩余时间为:[{}]秒,", tokenInfo, time);
            if (null == aBoolean) {
                aBoolean = false;
            }
            return aBoolean ? tokenInfo : (Map<String,String>)redisTemplate.opsForValue().get(tokenKey);
        } catch (Exception e) {
            logger.warn("登录失败:[{}]", e.getMessage(), e);
            throw new ApplicationException("登录失败:" + e.getMessage());
        }
    }

    @Cacheable(value= "crabapples:sysMenus",key = "#auth")
    @Override
    public List<SysMenu> getSysMenus(String auth) {
        return sysRepository.findAll();
    }
}
