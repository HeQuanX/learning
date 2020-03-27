package cn.crabapples.spring.system.service.websocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.websocket.Session;

/**
 * TODO webSocketService
 *
 * @author Mr.He
 *  2019/8/5 22:53
 * e-mail crabapples.cn@gmail.com
 * qq 294046317
 * pc-name 29404
 */
@Slf4j
@Service
public class MessageService {

    private WebSocketServer webSocketServer;

    public MessageService(WebSocketServer webSocketServer) {
        this.webSocketServer = webSocketServer;
    }

    public void sendMessage(String message, String sid) {
        Session session = webSocketServer.webSocketMap.get(sid);
        if (session == null) {
            return;
        }
        webSocketServer.sendMessage(session, "发往客户端的消息-->" + message);
    }
}
