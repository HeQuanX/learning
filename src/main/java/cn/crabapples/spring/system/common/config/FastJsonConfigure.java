package cn.crabapples.spring.common.config;

import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * TODO fastJson配置
 *
 * @author Mr.He
 * 2020/1/31 0:12
 * e-mail crabapples.cn@gmail.com
 * qq 294046317
 * pc-name 29404
 */
@Configuration
public class FastJsonConfigure {
    @Bean
    public FastJsonConfig fastJsonConfig() {
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setDateFormat("yyyy-MM-dd HH:mm:ss E");
        return fastJsonConfig;
    }
}
