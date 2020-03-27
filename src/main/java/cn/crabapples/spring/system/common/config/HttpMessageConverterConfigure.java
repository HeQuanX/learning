package cn.crabapples.spring.common.config;

import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * TODO springboot 消息转换配置(主要用于配置fastJson解析)
 *
 * @author Mr.He
 * 2020/1/31 2:02
 * e-mail crabapples.cn@gmail.com
 * qq 294046317
 * pc-name 29404
 */
@Configuration
public class HttpMessageConverterConfigure {
    @Bean
    public HttpMessageConverters httpMessageConverters(FastJsonConfig fastJsonConfig){
        FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter();
        fastJsonHttpMessageConverter.setFastJsonConfig(fastJsonConfig);
        return new HttpMessageConverters(fastJsonHttpMessageConverter);
    }
}
