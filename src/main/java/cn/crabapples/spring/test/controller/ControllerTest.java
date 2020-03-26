package cn.crabapples.spring.test.controller;

import cn.crabapples.spring.system.common.BaseController;
import cn.crabapples.spring.system.common.groups.IsNotNull;
import cn.crabapples.spring.system.common.groups.IsNull;
import cn.crabapples.spring.system.dto.ResponseDTO;
import cn.crabapples.spring.test.form.DemoPostForm1;
import cn.crabapples.spring.test.form.DemoPostForm2;
import cn.crabapples.spring.test.service.ServiceTest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * TODO 一个演示的controller
 *
 * @author Mr.He
 * 2019/8/5 22:51
 * e-mail crabapples.cn@gmail.com
 * qq 294046317
 * pc-name 29404
 */
@Api("测试接口")
@RestController
@RequestMapping(value = "/api/test")
public class ControllerTest extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(ControllerTest.class);
    private ServiceTest serviceTest;
    private final RabbitTemplate rabbitTemplate;

    public ControllerTest(ServiceTest serviceTest, RabbitTemplate rabbitTemplate) {
        this.serviceTest = serviceTest;
        this.rabbitTemplate = rabbitTemplate;
    }

    @GetMapping("/mq/send")
    public ResponseDTO mqSend(@RequestParam String name){
        Map<String,Object> map = new HashMap<>();
        map.put("name","小明");
        map.put("age",1);
        map.put("amt",9.9);
        map.put("time", LocalDateTime.now());
        rabbitTemplate.convertAndSend(name,map);
        System.out.println(rabbitTemplate);
        return ResponseDTO.returnSuccess("消息发送成功");
    }

    @GetMapping("/mq/get")
    public ResponseDTO mqGet(@RequestParam String name){
        Message message = rabbitTemplate.receive(name);
        System.err.println(message);
        return ResponseDTO.returnSuccess("消息消费成功",message);
    }


    @GetMapping("/hello")
    @ApiOperation(value = "hello,world测试", notes = "这个是一个测试接口")
    @ApiImplicitParam(name = "name", value = "这个是参数", required = true, dataType = "String", paramType = "query")
    public String helloDemo(@RequestParam String name){
        return name + " say: hello world";
    }

    /**
     * TODO 测试从RequestBody中获取数据并验证
     * @RequestBody 表示从请求体中获取数据
     * @Valid 表示需要验证对应属性
     *
     * 参数中传入BindingResult对象时，无论参数校验是否通过都会执行方法
     * 可通过bindingResult.hasErrors()判断参数校验是否通过
     */
    @PostMapping("/postDemo1")
    @ApiOperation(value = "属性校验测试", notes = "属性校验测试接口")
    public ResponseDTO postDemo1(@Valid @RequestBody DemoPostForm1 demoPostForm, BindingResult bindingResult){
        logger.info("提交的参数:{}",demoPostForm);
        if(bindingResult.hasErrors()){
            String errorMessage = bindingResult.getFieldErrors().get(0).getDefaultMessage();
            logger.error("验证信息:{}", errorMessage);
            return ResponseDTO.returnError(errorMessage);
        }
        return ResponseDTO.returnSuccess("成功",demoPostForm);
    }

    /**
     * TODO 测试使用不同的校验组校验参数，根据参数中的type判断使用哪个校验组
     */
    @PostMapping("/postDemo2")
    public ResponseDTO postDemo2(@RequestBody DemoPostForm2 demoPostForm){
        try {
            if (demoPostForm.getType() == 0) {
                super.validator(demoPostForm, IsNull.class);
            } else {
                super.validator(demoPostForm, IsNotNull.class);
            }
            return ResponseDTO.returnSuccess("收到");
        }catch (Exception e){
            assert false : "" ;
            return ResponseDTO.returnError(e.getMessage());
        }
    }

}
