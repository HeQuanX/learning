package cn.crabapples.spring.test.form;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;
import java.util.Calendar;
import java.util.Date;

/**
 * TODO 测试参数验证的Form
 *
 * @author Mr.He
 * 2019/9/19 23:46
 * e-mail crabapples.cn@gmail.com
 * qq 294046317
 * pc-name 29404
 */

/**
 * value	String	“”	属性简要说明
 * name	String	“”	运行覆盖属性的名称。重写属性名称
 * allowableValues	String	“”	限制参数可接收的值，三种方法，固定取值，固定范围
 * access	String	“”	过滤属性，参阅:io.swagger.core.filter.SwaggerSpecFilter
 * notes	String	“”	目前尚未使用
 * dataType	String	“”	参数的数据类型，可以是类名或原始数据类型，此值将覆盖从类属性读取的数据类型
 * required	boolean	false	是否为必传参数,false:非必传参数; true:必传参数
 * position	int	0	允许在模型中显示排序属性
 * hidden	boolean	false	隐藏模型属性，false:不隐藏; true:隐藏
 * example	String	“”	属性的示例值
 * readOnly	boolean	false	指定模型属性为只读，false:非只读; true:只读
 * reference	String	“”	指定对应类型定义的引用，覆盖指定的任何其他元数据
 * allowEmptyValue	boolean	false	允许传空值，false:不允许传空值; true:允许传空值
 */
@Getter
@Setter
@ApiModel
public class DemoPostForm1 {
    /**
     * 必须为空
     */
    @Null(message = "ID必须为空")
    @ApiModelProperty(value = "说明", example = "id001", dataType = "String")
    private String id;
    /**
     * 不能为空
     */
    @NotBlank(message = "名字不能为空")
    @ApiModelProperty(value = "姓名", example = "张三", dataType = "String")
    private String name;
    /**
     * 大于等于0
     */
    @PositiveOrZero(message = "年龄有误")
    @ApiModelProperty(value = "年龄", example = "20", dataType = "String")
    private int age;
    /**
     * 小于等于0
     */
    @NegativeOrZero(message = "存款有误")
    @ApiModelProperty(value = "存款", example = "999", dataType = "String")
    private int money;

    /**
     * 当前时间之后
     */
    @FutureOrPresent(message = "时间有误")
    @ApiModelProperty(value = "创建时间", example = "2020-01-01", dataType = "String")
    private Date createTime;

    /**
     * 当前时间之前
     */
    @PastOrPresent(message = "时间有误")
    @ApiModelProperty(value = "更新时间", example = "2020-01-19", dataType = "java.lang.String")
    private Calendar updateTime;

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
