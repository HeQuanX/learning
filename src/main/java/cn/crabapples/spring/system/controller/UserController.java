package cn.crabapples.spring.system.controller;

import cn.crabapples.spring.common.BaseController;
import cn.crabapples.spring.common.groups.IsAdd;
import cn.crabapples.spring.common.groups.IsEdit;
import cn.crabapples.spring.common.groups.IsStatusChange;
import cn.crabapples.spring.system.dto.ResponseDTO;
import cn.crabapples.spring.system.entity.User;
import cn.crabapples.spring.system.form.UserForm;
import cn.crabapples.spring.system.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


/**
 * TODO 用户管理接口类
 *
 * @author Mr.He
 * 2020/1/27 2:09
 * e-mail crabapples.cn@gmail.com
 * qq 294046317
 * pc-name 29404
 */
@Controller
@ResponseBody
@RequestMapping(value = "/api/user")
@Api("用户管理")
public class UserController extends BaseController {
    private Logger logger = LoggerFactory.getLogger(UserController.class);
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/addUser")
    @ApiOperation(value = "添加用户", notes = "添加用户接口")
    public ResponseDTO addUser(@RequestBody UserForm form){
        logger.info("收到请求->添加用户:[{}]",form);
        super.validator(form, IsAdd.class);
        User user = userService.addUser(form);
        logger.info("用户添加完成->用户信息:[{}]",user);
        return ResponseDTO.returnSuccess("操作成功",user);
    }

    @PostMapping("/editUser")
    @ApiOperation(value = "修改用户", notes = "修改用户接口")
    public ResponseDTO editUser(@RequestBody UserForm form){
        logger.info("收到请求->修改用户:[{}]",form);
        super.validator(form, IsEdit.class);
        User user = userService.editUser(form);
        logger.info("用户修改完成->用户信息:[{}]",user);
        return ResponseDTO.returnSuccess("操作成功",user);
    }

    @PostMapping("/delUser")
    @ApiOperation(value = "删除用户", notes = "删除用户接口")
    public ResponseDTO delUser(@RequestBody UserForm form){
        super.validator(form, IsStatusChange.class);
        logger.info("收到请求->删除用户:[{}]",form.getId());
        userService.delUser(form.getId());
        logger.info("用户删除完成");
        return ResponseDTO.returnSuccess("操作成功");
    }

    @PostMapping("/unActiveUser")
    @ApiOperation(value = "禁用用户", notes = "禁用用户接口")
    public ResponseDTO unActiveUser(@RequestBody UserForm form){
        super.validator(form, IsStatusChange.class);
        logger.info("收到请求->禁用用户:[{}]",form.getId());
        userService.unActiveUser(form.getId());
        logger.info("用户禁用完成");
        return ResponseDTO.returnSuccess("操作成功");
    }

    @PostMapping("/activeUser")
    @ApiOperation(value = "激活用户", notes = "激活用户接口")
    public ResponseDTO activeUser(@RequestBody UserForm form){
        super.validator(form, IsStatusChange.class);
        logger.info("收到请求->激活用户:[{}]",form.getId());
        userService.activeUser(form.getId());
        logger.info("用户激活完成");
        return ResponseDTO.returnSuccess("操作成功");
    }

}
