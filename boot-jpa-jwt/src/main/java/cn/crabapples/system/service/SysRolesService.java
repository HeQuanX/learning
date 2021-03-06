package cn.crabapples.system.service;

import cn.crabapples.common.PageDTO;
import cn.crabapples.common.base.BaseService;
import cn.crabapples.system.dto.SysRolesDTO;
import cn.crabapples.system.entity.SysRoles;
import cn.crabapples.system.form.RolesForm;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * TODO 系统相关服务[角色]
 *
 * @author Mr.He
 * 2021/4/25 0:34
 * e-mail crabapples.cn@gmail.com
 * qq 294046317
 * pc-name mrhe
 */
public interface SysRolesService extends BaseService {

    List<SysRolesDTO> getUserRoles(HttpServletRequest request);

    List<SysRoles> getRolesList(HttpServletRequest request);

    List<SysRoles> getRolesPage(HttpServletRequest request, PageDTO page);

    SysRoles saveRoles(RolesForm form);

    SysRoles removeRoles(String id);
}
