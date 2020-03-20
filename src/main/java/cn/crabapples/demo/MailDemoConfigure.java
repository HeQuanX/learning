package cn.crabapples.demo;


import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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
public class MailDemoConfigure {
    private String host = "smtp.qq.com";
    private String source = "xxx@qq.com";
    private String port = "465";
    private String username = "xxx@qq.com";
    private String password = "password";;
    private boolean isAuth = true;
    private boolean isDebug = true;
    private String protocol = "smtp";
    private String socketFactory = "javax.net.ssl.SSLSocketFactory";
    private String[] targets = {"xxxxxx@qq.com"};

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
