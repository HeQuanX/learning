package cn.crabapples.system.controller;

import cn.crabapples.common.Groups;
import cn.crabapples.common.dto.ResponseDTO;
import cn.crabapples.system.common.BaseController;
import cn.crabapples.system.entity.SysMenu;
import cn.crabapples.system.form.UserForm;
import cn.crabapples.system.service.SysService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * TODO 系统相关接口
 *
 * @author Mr.He
 * 2020/1/28 23:17
 * e-mail crabapples.cn@gmail.com
 * qq 294046317
 * pc-name 29404
 */

@Controller
@Api("系统管理")
public class SysController extends BaseController {

    private final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final SysService sysService;

    public SysController(SysService sysService) {
        this.sysService = sysService;
    }

    /**
     * 进入登录页面
     * @return 返回登录页面html
     */
    @GetMapping("/toLoginPage")
    public String toLoginPage(){
        logger.info("收到请求->进入登录页面");
        return "login";
    }

    /**
     * 进入主页面
     * @return 登录后的主页面
     */
    @GetMapping("/")
    public String index(){
        logger.info("收到请求->进入主页");
        return "index";
    }

    /**
     * 发起登录请求
     * @param form 用户名和密码
     * @return 登录成功返回token
     */
    @PostMapping("/login")
    @ResponseBody
    @ApiOperation(value = "用户登陆", notes = "用户登陆接口")
    public ResponseDTO login(@RequestBody UserForm form){
        logger.info("收到请求->用户登陆:[{}]",form);
        super.validator(form, Groups.IsLogin.class);
        Map<String,String> tokenInfo = sysService.login(form);
        logger.info("登录成功->用户信息:[{}]",tokenInfo);
        return ResponseDTO.returnSuccess("操作成功",tokenInfo);
    }

    /**
     * 获取系统菜单
     * @param auth 登录授权信息
     * @return 返回当前用户拥有的系统菜单
     */
    @GetMapping("/getSysMenus")
    @ResponseBody
    public ResponseDTO getSysMenus(String auth){
        logger.info("收到请求->获取菜单列表");
        List<SysMenu> menus = sysService.getSysMenus(auth);
        logger.info("获取菜单列表成功:[{}]",menus);
        return ResponseDTO.returnSuccess("操作成功",menus);
    }

}
