package cn.harveychan.kaimaa.po;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

/**
 * 项目
 */
@Data
@NoArgsConstructor
@ToString
@Entity
@Table(name = "project")
public class Project {

    /** 主键ID */
    @Id
    private Long id;
    /** 名称 */
    private String name;
    /** 类型 */
    private String type;
    /** 描述 */
    private String description;
    /** 图片 */
    private String picture;
    /** 网址 */
    private String url;
    /** 创建时间 */
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    /** 更新时间 */
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;
}
