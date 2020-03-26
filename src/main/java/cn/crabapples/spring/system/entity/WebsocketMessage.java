package cn.crabapples.spring.system.entity;

import com.alibaba.fastjson.JSON;

/**
 * TODO
 *
 * @author Mr.He
 *  2019/8/5 22:52
 * e-mail crabapples.cn@gmail.com
 * qq 294046317
 * pc-name 29404
 */
public class WebsocketMessage {

    private String sid;

    private String message;

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
