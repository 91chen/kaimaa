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
 * 分类
 */
@Data
@NoArgsConstructor
@ToString
@Entity
@Table(name = "type")
public class Type implements Serializable {

    /** 主键ID */
    @Id
    private Long id;
    /** 名称 */
    @NotBlank(message = "分类名称不能为空")
    private String name;
    /** 创建时间 */
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    /** 更新时间 */
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;

    @OneToMany(mappedBy = "type")
    private List<Blog> blogs = new ArrayList<>();
}
