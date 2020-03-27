package cn.crabapples.spring.crabapples.common.config;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
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
    ShiroRealm shiroRealm;

    public ShiroConfigure(ShiroRealm shiroRealm) {
        this.shiroRealm = shiroRealm;
    }

    /**
     * shiro过滤器
     *
     * 添加创建securityManager的工厂类注入Bean
     * @return ShiroFilterFactoryBean
     */
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactory = new ShiroFilterFactoryBean();
        shiroFilterFactory.setSecurityManager(securityManager);
        Map<String,String> filterMap = new HashMap<>();
        filterMap.put("/**","authc");
        filterMap.put("/login","anon");

        filterMap.put("/js/**","anon");
        filterMap.put("/css/**","anon");
        filterMap.put("/X-admin/css/**","anon");
        filterMap.put("/X-admin/js/**","anon");
        filterMap.put("/X-admin/images/**","anon");
        filterMap.put("/X-admin/fonts/**","anon");
        filterMap.put("/X-admin/lib/**","anon");


        shiroFilterFactory.setFilterChainDefinitionMap(filterMap);
        shiroFilterFactory.setLoginUrl("/");
        return shiroFilterFactory;
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

}
