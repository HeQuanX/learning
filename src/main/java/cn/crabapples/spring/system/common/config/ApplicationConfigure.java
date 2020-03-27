package cn.crabapples.spring.common.config;

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
    public String AES_KEY;
    public String REDIS_PREFIX;
    public Long TOKEN_CACHE_TIME;

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
