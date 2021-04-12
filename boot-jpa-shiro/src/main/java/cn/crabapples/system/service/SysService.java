package cn.crabapples.system.service;

import cn.crabapples.system.entity.SysMenu;
import cn.crabapples.system.form.UserForm;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * TODO 系统相关服务
 *
 * @author Mr.He
 * 2020/1/28 23:22
 * e-mail crabapples.cn@gmail.com
 * qq 294046317
 * pc-name 29404
 */
public interface SysService {
    String loginCheck(UserForm form);

    List<SysMenu> getSysMenus(HttpServletRequest request);
}
