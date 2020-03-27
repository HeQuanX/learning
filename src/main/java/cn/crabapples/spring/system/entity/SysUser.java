package cn.crabapples.spring.system.entity;

import cn.crabapples.spring.system.common.BaseEntity;
import cn.crabapples.spring.system.common.groups.IsNotNull;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.LastModifiedBy;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * TODO 用户实体类
 *
 * @author Mr.He
 * 2019/7/4 14:51
 * e-mail crabapples.cn@gmail.com
 * qq 294046317
 * pc-name 29404
 *
 * Entity 表示这是一个和数据库表相关联的类
 */
@Entity
@Getter
@Setter
public class SysUser extends BaseEntity {
    public SysUser() {}
    public SysUser(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    @Column(columnDefinition = "varchar(32) comment '用户名'",unique=true)
    @NotBlank(message = "用户名不能为空", groups = IsNotNull.class)
    private String username;

    @Column(columnDefinition = "varchar(64) comment '密码'")
    @NotBlank(message = "密码不能为空", groups = IsNotNull.class)
    private String password;

    @Column(columnDefinition = "varchar(32) comment '姓名'")
    @NotBlank(message = "姓名不能为空", groups = IsNotNull.class)
    private String name;

    /**
     * Column nullable = false 数据字段不能为空
     */
    @Column(columnDefinition = "int (3) default 0 not null comment '年龄'")
    @NotNull(message = "年龄不能为空", groups = IsNotNull.class)
    private Integer age;

    @LastModifiedBy
    @Column(columnDefinition = "varchar(32) comment '最后操作用户'")
    private String lastModifiedBy;

    @ManyToMany(fetch = FetchType.EAGER)
    @JSONField(serialize = false)
    private List<SysRole> sysRoles;

    @Column(columnDefinition = "bit(1) default 0 not null comment '用户状态标记 0:正常 1:禁用'")
    @NotBlank(message = "状态不能为空", groups = IsNotNull.class)
    private Integer status;

    @Column(columnDefinition = "bit(1) default 1 not null comment '是否为超级管理员状态标记 0:是 1:否'")
    private Integer isAdmin;

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}