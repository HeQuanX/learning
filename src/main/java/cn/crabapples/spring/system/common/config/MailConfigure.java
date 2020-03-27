package cn.crabapples.spring.system.common.config;


import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * TODO 邮件配置类
 *
 * @author Mr.He
 * 2020/3/20 21:15
 * e-mail crabapples.cn@gmail.com
 * qq 294046317
 * pc-name 29404
 */
@Getter
@Setter
@Configuration
public class MailConfigure {
    @Value("${mail.host}")
    private String host;
    @Value("${mail.source}")
    private String source;
    @Value("${mail.port}")
    private String port;
    @Value("${mail.username}")
    private String username;
    @Value("${mail.password}")
    private String password;
    @Value("${mail.isAuth:true}")
    private boolean isAuth;
    @Value("${mail.isDebug:true}")
    private boolean isDebug;
    @Value("${mail.protocol:smtp}")
    private String protocol;
    @Value("${mail.socketFactory:javax.net.ssl.SSLSocketFactory}")
    private String socketFactory;
    private String[] targets = {};

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
