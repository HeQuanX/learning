<<<<<<< HEAD:src/main/java/cn/crabapples/spring/system/common/config/ShiroConfigure.java
package cn.crabapples.spring.system.common.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
=======
package cn.crabapples.spring.crabapples.common.config;

>>>>>>> cf9c3256dbf50c1620eeaec4a4f648745556d2b2:src/main/java/cn/crabapples/spring/crabapples/common/config/ShiroConfigure.java
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * TODO shiro配置类
 *
 * @author Mr.He
 * 2020/3/5 0:57
 * e-mail crabapples.cn@gmail.com
 * qq 294046317
 * pc-name 29404
 */
@Configuration
public class ShiroConfigure {
    cn.crabapples.spring.system.common.config.ShiroRealm shiroRealm;

    public ShiroConfigure(cn.crabapples.spring.system.common.config.ShiroRealm shiroRealm) {
        this.shiroRealm = shiroRealm;
    }

    /**
     * 创建securityManager类注入的Bean
     * @return DefaultWebSecurityManager
     */
    @Bean
    public DefaultWebSecurityManager getDefaultWebSecurityManager(ShiroRealm shiroRealm){
        DefaultWebSecurityManager webSecurityManager = new DefaultWebSecurityManager();
        webSecurityManager.setRealm(shiroRealm);
        return webSecurityManager;
    }

    /**
     * 创建shiro过滤器工厂添加到shiroManger中(用于拦截url)
     * @return shiro过滤器工厂
     */
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactory = new ShiroFilterFactoryBean();
        shiroFilterFactory.setSecurityManager(securityManager);
        Map<String,String> filterMap = new HashMap<>();
        /*
         * shiro过滤器配置
         *  authc需要认证的url
         *  anon不需要认证的url
         *  perms需要授权的url
         */
        filterMap.put("/**","authc");
        filterMap.put("/login","anon");

        filterMap.put("/js/**","anon");
        filterMap.put("/css/**","anon");
        filterMap.put("/X-admin/css/**","anon");
        filterMap.put("/X-admin/js/**","anon");
        filterMap.put("/X-admin/images/**","anon");
        filterMap.put("/X-admin/fonts/**","anon");
        filterMap.put("/X-admin/lib/**","anon");
        filterMap.put("/api/test/**","anon");


        shiroFilterFactory.setFilterChainDefinitionMap(filterMap);
        shiroFilterFactory.setLoginUrl("/");
<<<<<<< HEAD:src/main/java/cn/crabapples/spring/system/common/config/ShiroConfigure.java
        /*
         * ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑
         * 认证失败跳转的页面
         *
         * 授权失败跳转的页面
         * ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓
         */
        shiroFilterFactory.setUnauthorizedUrl("/");
=======
>>>>>>> cf9c3256dbf50c1620eeaec4a4f648745556d2b2:src/main/java/cn/crabapples/spring/crabapples/common/config/ShiroConfigure.java
        return shiroFilterFactory;
    }



    /**
     * 开启shiro对thymeleaf标签的支持
     */
    @Bean
    public ShiroDialect getShiroDialect (){
        return new ShiroDialect();
    }

<<<<<<< HEAD:src/main/java/cn/crabapples/spring/system/common/config/ShiroConfigure.java

    /*
     * 配置以下两个bean(DefaultAdvisorAutoProxyCreator和AuthorizationAttributeSourceAdvisor)即可实现此功能
     */
    /**
     *  开启Shiro的注解验证过滤功能(如@RequiresRoles,@RequiresPermissions)
     *  需借助SpringAOP扫描使用Shiro注解的类,并在必要时进行安全逻辑验证
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }

    /**
     * 开启shiro对SpringAop注解支持
     * @param securityManager shiro安全管理器
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }
=======
>>>>>>> cf9c3256dbf50c1620eeaec4a4f648745556d2b2:src/main/java/cn/crabapples/spring/crabapples/common/config/ShiroConfigure.java
}
