package cn.crabapples.spring.system.service.impl;

import cn.crabapples.spring.system.common.config.ApplicationConfigure;
import cn.crabapples.spring.system.dao.SysMenuRepository;
import cn.crabapples.spring.system.dto.ResponseDTO;
import cn.crabapples.spring.system.entity.SysMenu;
import cn.crabapples.spring.system.entity.SysUser;
import cn.crabapples.spring.system.form.UserForm;
import cn.crabapples.spring.system.service.SysService;
import cn.crabapples.spring.system.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.*;

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
    private String salt;

    private final UserService userService;

    private final SysMenuRepository sysMenuRepository;

    private final RedisTemplate<String, Object> redisTemplate;

    public SysServiceImpl(ApplicationConfigure applicationConfigure,
                          UserService userService,
                          SysMenuRepository sysMenuRepository,
                          RedisTemplate<String, Object> redisTemplate) {
        this.userService = userService;
        this.sysMenuRepository = sysMenuRepository;
        this.redisTemplate = redisTemplate;
        this.aesKey = applicationConfigure.AES_KEY;
        this.redisPrefix = applicationConfigure.REDIS_PREFIX;
        this.tokenCacheTime = applicationConfigure.TOKEN_CACHE_TIME;
        this.salt = applicationConfigure.SALT;
    }

    /**
     * Cacheable
     * * key: redis中key的值
     * * value: redis中key的前缀
     * * 例:
     * * key::value:tom
     * * userLogin::${#p0.username}
     * <p>
     * 用户登录验证
     *
     * @param form 用户信息
     * @return token
     */
//    @Cacheable(value = "login:token", key = "#p0.username")
    @Override
    public ResponseDTO login(UserForm form) {
        try {
            String username = form.getUsername();
            String password = new Md5Hash(form.getPassword(), salt).toString();
            logger.info("开始登录->用户名:[{}],密码:[{}]", username, password);
            SysUser user = shiroCheckLogin(username, password);
            if (user == null) {
                return ResponseDTO.returnError("用户名或密码错");
            }
        } catch (Exception e) {
            logger.warn("登录失败:[{}]", e.getMessage(), e);
            return ResponseDTO.returnError(e.getMessage());
        }
        return ResponseDTO.returnSuccess("登录成功");
    }

    /**
     * shiro认证
     *
     * @param username 用户名
     * @param password 密码
     * @return 认证成功后的用户对象
     */
    private SysUser shiroCheckLogin(String username, String password) {
        try {
            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            subject.login(token);
            return (SysUser) subject.getPrincipal();
        } catch (IncorrectCredentialsException e) {
            logger.warn("shiro认证失败,密码错误:[{}]", e.getMessage());
        } catch (UnknownAccountException e) {
            logger.warn("shiro认证失败,用户不存在:[{}]", e.getMessage());
        } catch (Exception e) {
            logger.error("shiro认证失败", e);
        }
        return null;
    }

    /**
     * 获取当前用户拥有的菜单
     *
     * @return 当前用户拥有的菜单
     */
    //    @Cacheable(value = "crabapples:sysMenus", key = "#auth")
    @Override
    public List<SysMenu> getSysMenus(SysUser user) {
//        Subject subject = SecurityUtils.getSubject();
//        Object object = subject.getSession().getAttribute("user");
//        System.err.println(object);
        List<SysMenu> menus = sysMenuRepository.findByParentIdIsNull();
        insertChildrenMenus(menus);
        menus.forEach(System.err::println);
        return menus;
    }

//


//    @Override
//    public List<SysMenu> getSysMenus(SysUser user) {
//        logger.info("开始获取用户:[{}]已授权的菜单", user.getId());
//        List<SysMenu> userMenus = new ArrayList<>();
//        user.getSysRoles().forEach(e -> userMenus.addAll(e.getSysMenus()));
//        logger.debug("用户:[{}]已授权的菜单为:[{}]即将开始格式化菜单", user.getId(), userMenus);
//        List<SysMenu> allMenuTreesTemp = insertChildrenMenus(sysMenuRepository.findByParentIdIsNull());
//        userMenus.forEach(e -> filterChildrenMenus(e, allMenuTreesTemp));
//        userMenus.forEach(e -> filterParentMenus(allMenuTreesTemp));
//        logger.debug("用户:[{}]菜单格式化完毕:[{}]", user.getId(), allMenuTreesTemp);
//        logger.debug("开始过滤用户:[{}]没有权限的菜单:[{}]", user.getId(), allMenuTreesTemp);
//        List<SysMenu> sysMenus = removeMenus(allMenuTreesTemp);
//        logger.debug("用户:[{}]没有权限的菜单过滤完毕:[{}]", user.getId(), allMenuTreesTemp);
//        return sysMenus;
//    }

    /**
     * 移除菜单树中显示标识为false的菜单
     * @param sysMenus 数据库中的菜单列表(树状格式)
     * @return 移除后的菜单树
     */
    private List<SysMenu> removeMenus(List<SysMenu> sysMenus) {
        for (int i = sysMenus.size() - 1; i >= 0; i--) {
            sysMenus.get(i).setChildren(removeMenus(sysMenus.get(i).getChildren()));
            if (sysMenus.get(i).getChildren().size() == 0) {
                if (!sysMenus.get(i).isShowFlag()) {
                    sysMenus.remove(sysMenus.get(i));
                }
            }
        }
        return sysMenus;
    }

    /**
     * 将用户菜单中的对应下级显示标识改为true
     * @param allMenu 数据库中的菜单列表(树状格式)
     */
    private static void filterChildrenMenus(SysMenu userMenu, List<SysMenu> allMenu) {
        allMenu.forEach(e -> {
            filterChildrenMenus(userMenu, e.getChildren());
            if (userMenu.getId().equals(e.getId())) {
                e.setShowFlag(userMenu.getId().equals(e.getId()));
            }
        });
    }

    /**
     * 将用户菜单中的对应上级显示标识改为true
     * @param allMenu 数据库中的菜单列表(树状格式)
     */
    private static void filterParentMenus(List<SysMenu> allMenu) {
        allMenu.forEach(e -> {
            filterParentMenus(e.getChildren());
            e.getChildren().forEach(r -> {
                if (r.isShowFlag()) {
                    e.setShowFlag(true);
                }
            });
        });
    }

    /**
     * 格式化菜单树
     * @param menus 上级菜单列表(普通列表)
     * @return 加入下一级后的菜单列表
     */
    private List<SysMenu> insertChildrenMenus(List<SysMenu> menus) {
        menus.forEach(e -> {
            List<SysMenu> children = sysMenuRepository.findByParentId(e.getId());
            insertChildrenMenus(children);
            e.setChildren(children);
        });
        return menus;
    }
}