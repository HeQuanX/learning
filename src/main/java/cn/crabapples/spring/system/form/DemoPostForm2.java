package cn.crabapples.spring.system.form;

import cn.crabapples.spring.common.groups.IsNotNull;
import cn.crabapples.spring.common.groups.IsNull;
import com.alibaba.fastjson.JSONObject;
import lombok.Setter;

import javax.validation.constraints.*;

/**
 * TODO 测试参数验证的DTO
 *
 * @author Mr.He
 * @date 2019/9/21 18:46
 * e-mail crabapples.cn@gmail.com
 * qq 294046317
 * pc-name 29404
 */
@Setter
//@Api
public class DemoPostForm2 {
    @Null(message = "ID必须为空",groups = {IsNull.class})
    @NotNull(message = "ID不能为空",groups = {IsNotNull.class})
    private String id;
    @Size(min = 2,max = 5,message = "姓名有误")
    private String name;

    @Min(value = 0, message = "类型错误")
    @Max(value = 1, message = "类型错误")
    @NotEmpty(message = "类型不能为空")
    private int type;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getType() {
        return type;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
