package cn.crabapples.system.form;

import cn.crabapples.common.Groups;
import cn.crabapples.common.base.BaseForm;
import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * TODO 用户菜单Form
 *
 * @author Mr.He
 * 2021/4/12 15:42
 * e-mail crabapples.cn@gmail.com
 * qq 294046317
 * pc-name mrhe
 */
@Getter
@Setter
public class MenusForm extends BaseForm {
    private Integer sort;

    private String icon;

    @NotBlank(message = "菜单名称不能为空", groups = {Groups.IsAdd.class, Groups.IsEdit.class})
    private String name;

    private String url;

    //    @Column(columnDefinition = "int(2) default 0 comment '菜单类型 1:目录 2:菜单 3:外链 4:按钮'")
    @NotNull(message = "菜单类型不能为空", groups = {Groups.IsAdd.class, Groups.IsEdit.class})
    private Integer menusType;

    //    @Column(columnDefinition = "int(2) default 1 comment '菜单等级'")
    private Integer level;

    private String path;

    private String filePath;

    private String permission;

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
