package cn.crabapples.spring.system.entity;

import cn.crabapples.spring.common.BaseEntity;
import cn.crabapples.spring.system.groups.IsNotNull;
import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
 * Table name = "user" 设置表名为 user
 */
@Entity
@Table(name = "user")
@Getter
@Setter
public class User extends BaseEntity {
    public User() {}
    public User(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    /**
     * Column length=32 数据字段长度为32
     */
//    @Column(length = 32)
    @Column(columnDefinition = "varchar(32) comment '用户名'",unique=true)
    @NotBlank(message = "用户名不能为空", groups = IsNotNull.class)
    private String username;

    /**
     * Column length=256 数据字段长度为256
     */
    @Column(columnDefinition = "varchar(256) comment '密码'")
    @NotBlank(message = "密码不能为空", groups = IsNotNull.class)
    private String password;

    /**
     * Column length=32 数据字段长度为32
     */
    @Column(columnDefinition = "varchar(32) comment '姓名'")
    @NotBlank(message = "姓名不能为空", groups = IsNotNull.class)
    private String name;

    /**
     * Column nullable = false 数据字段不能为空
     */
    @Column(columnDefinition = "int (3) default 0 not null comment '年龄'")
    @NotNull(message = "年龄不能为空", groups = IsNotNull.class)
    private Integer age;
    /**
     * 状态标记 0:正常 1:禁用
     */
    @Column(columnDefinition = "bit(1) default 0 not null comment '状态标记'")
    @NotBlank(message = "状态不能为空", groups = IsNotNull.class)
    private int status;

    /**
     * 状态标记 0:是 1:否
     */
    @Column(columnDefinition = "bit(1) default 1 not null comment '是否为超级管理员'")
    private int isAdmin;

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}