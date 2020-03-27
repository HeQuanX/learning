package cn.crabapples.spring.system.controller;

import cn.crabapples.spring.system.entity.WebsocketMessage;
import cn.crabapples.spring.system.service.websocket.MessageService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * TODO WebSocketController
 *
 * @author Mr.He
 * 2019/8/5 22:50
 * e-mail crabapples.cn@gmail.com
 * qq 294046317
 * pc-name 29404
 */
//@Controller
//@RequestMapping("/websocket/")
public class WebSocketController {

    private final MessageService messageService;

    public WebSocketController(MessageService messageService) {
        this.messageService = messageService;
    }

    @RequestMapping("index")
    public String index() {
        return "SocketDemo";
    }

    @ResponseBody
    @RequestMapping(value = "/sendMessage", method = RequestMethod.POST)
    public void sengMeaasge(@RequestBody WebsocketMessage message) {
        messageService.sendMessage(message.getMessage(), message.getSid());
    }
}
