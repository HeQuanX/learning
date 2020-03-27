package cn.crabapples.spring.system.common.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
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
        fastJsonConfig.setSerializerFeatures(
                SerializerFeature.WriteEnumUsingToString,
                SerializerFeature.WriteNullStringAsEmpty,
                SerializerFeature.WriteMapNullValue,
                SerializerFeature.WriteNullListAsEmpty,
                SerializerFeature.WriteDateUseDateFormat);
//        fastJsonConfig.setSerializeFilters((ValueFilter) (o, s, source) -> {
//            if (source == null) {
//                /*
//                 * 如果返回对象的变量为null,则自动变成""
//                 */
//                return "";
//            }
//            return source;
//        });


        return fastJsonConfig;
    }
}
