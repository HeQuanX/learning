package cn.crabapples.spring.system.form;

import cn.crabapples.spring.system.common.groups.IsEdit;
import cn.crabapples.spring.system.entity.SysUser;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;

/**
 * TODO 用户信息提交Form
 *
 * @author Mr.He
 * 2020/1/27 11:26
 * e-mail crabapples.cn@gmail.com
 * qq 294046317
 * pc-name 29404
 */
@Data
@ApiModel("登录")
public class UserForm {
    @NotBlank(message = "id不能为空", groups = cn.crabapples.spring.system.common.groups.IsEdit.class)
    @NotBlank(message = "id不能为空", groups = cn.crabapples.spring.system.common.groups.IsStatusChange.class)
    @Null(message = "id必须为空", groups = cn.crabapples.spring.system.common.groups.IsAdd.class)
    private String id;

    @Length(max = 32 ,message = "长度错误",groups = {cn.crabapples.spring.system.common.groups.IsCheckLength.class, cn.crabapples.spring.system.common.groups.IsLogin.class})
    @NotBlank(message = "用户名不能为空", groups = {cn.crabapples.spring.system.common.groups.IsNotNull.class, cn.crabapples.spring.system.common.groups.IsLogin.class})
    @ApiModelProperty(example = "111")
    private String username;

    @Length(max = 32 ,message = "长度错误",groups = {cn.crabapples.spring.system.common.groups.IsCheckLength.class, cn.crabapples.spring.system.common.groups.IsLogin.class})
    @NotBlank(message = "密码不能为空", groups = {cn.crabapples.spring.system.common.groups.IsNotNull.class, cn.crabapples.spring.system.common.groups.IsLogin.class})
    @ApiModelProperty(example = "123456")
    private String password;

    @Length(max = 32)
    @NotBlank(message = "姓名不能为空", groups = {cn.crabapples.spring.system.common.groups.IsAdd.class, cn.crabapples.spring.system.common.groups.IsEdit.class})
    private String name;

    @Length(max = 3)
    @NotNull(message = "年龄不能为空", groups = {cn.crabapples.spring.system.common.groups.IsAdd.class, IsEdit.class})
    private Integer age;

    public cn.crabapples.spring.system.entity.SysUser toEntity(){
        cn.crabapples.spring.system.entity.SysUser user = new SysUser();
        user.setId(this.id);
        user.setName(this.name);
        user.setAge(this.age);
        user.setUsername(this.username);
        user.setPassword(this.password);
        return user;
    }
}
