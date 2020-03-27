package cn.crabapples.spring.common;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * TODO 实体类基础属性
 *
 * @author Mr.He
 * 2019/9/21 17:47
 * e-mail crabapples.cn@gmail.com
 * qq 294046317
 * pc-name 29404
 */
@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity implements Serializable {
    /**
     * Id uuid主键
     * GeneratedValue 自增长
     */
    @Id
    @Column(length = 64)
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    /**
     * 创建时间
     * columnDefinition 设置默认值为当前时间
     */
    @Column(columnDefinition = "timestamp default current_timestamp comment '创建时间'")
    @CreatedDate
    @JSONField(format = "yyyy-MM-dd HH:mm:ss E")
    private LocalDateTime createTime;

    /**
     * 更新时间
     * columnDefinition 设置默认值为当前时间，随每次更新数据时更新时间
     */
    @Column(columnDefinition = "timestamp default current_timestamp on update current_timestamp comment '修改时间'")
    @LastModifiedDate
    @JSONField(format = "yyyy-MM-dd HH:mm:ss E")
    private LocalDateTime updateTime;

    /**
     * 删除标记 (0:正常 1:删除)
     */
    @Column(columnDefinition = "bit(1) default 0 not null comment '删除标记'")
    private int delFlag;

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
