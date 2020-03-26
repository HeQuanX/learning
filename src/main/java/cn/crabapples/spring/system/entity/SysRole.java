package cn.crabapples.spring.system.entity;

import cn.crabapples.spring.system.common.BaseEntity;
import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * TODO 角色实体类
 *
 * @author Mr.He
 * 2020/3/7 1:30
 * e-mail crabapples.cn@gmail.com
 * qq 294046317
 * pc-name 29404
 */
@Entity
@Getter
@Setter
public class SysRole extends BaseEntity {
    @Column(columnDefinition = "varchar(64) ")
    private String name;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<SysMenu> sysMenus;

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
