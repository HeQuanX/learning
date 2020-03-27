package cn.crabapples.spring.system.entity;

import cn.crabapples.spring.common.BaseEntity;
import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;
import java.util.List;

/**
 * TODO 系统菜单
 *
 * @author Mr.He
 * 3/1/20 11:58 PM
 * e-mail crabapples.cn@gmail.com
 * qq 294046317
 * pc-name root
 */
@Setter
@Getter
@Entity
public class SysMenu extends BaseEntity {
    @Column(columnDefinition = "varchar(64) comment '菜单图标'")
    private String icon;

    @Column(columnDefinition = "varchar(64) comment '菜单名'")
    private String name;

    @Column(columnDefinition = "varchar(64) comment '菜单链接'")
    private String url;

    @Column(columnDefinition = "int(2) comment '菜单类型 1:菜单树 2:菜单 3:外部链接'")
    private String menuType;

    @Column(columnDefinition = "varchar(64) comment '上级菜单id'")
    private String parentId;

    @Transient
    private List<SysMenu> children;

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
