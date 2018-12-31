package cn.harveychan.kaimaa.po;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 标签
 */
@Data
@NoArgsConstructor
@ToString
@Entity
@Table(name = "tag")
public class Tag implements Serializable {

    /** 主键ID */
    @Id
    private Long id;
    /** 标签名称 */
    @NotBlank(message = "标签名称不能为空")
    private String name;
    /** 创建时间 */
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    /** 更新时间 */
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;

    @ManyToMany(mappedBy = "tags")
    private List<Blog> blogs = new ArrayList<>();
}
