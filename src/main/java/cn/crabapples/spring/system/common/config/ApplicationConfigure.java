package cn.crabapples.spring.system.common.config;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * TODO 应用配置类
 *
 * @author Mr.He
 * 3/2/20 1:21 AM
 * e-mail crabapples.cn@gmail.com
 * qq 294046317
 * pc-name root
 */
@Component
@ConfigurationProperties(prefix= "crabapples")
@Getter
@Setter
public class ApplicationConfigure {
    /**
     * aesKey种子
     */
    public String AES_KEY;
    /**
     * redis缓存前缀
     */
    public String REDIS_PREFIX;
    /**
     * token缓存时间
     */
    public Long TOKEN_CACHE_TIME;
    /**
     * MD5加密盐值
     */
    public String SALT;

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
