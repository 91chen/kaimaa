package cn.harveychan.kaimaa.po;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 博客
 */
@Data
@NoArgsConstructor
@ToString
@Entity
@Table(name = "blog")
public class Blog implements Serializable {

    /** 主键ID  */
    @Id
    private Long id;
    /** 标题 */
    private String title;
    /** 内容 */
    @Basic(fetch = FetchType.LAZY)
    @Lob
    private String content;
    /** 首图 */
    private String firstPicture;
    /** 标识 */
    private String flag;
    /** 浏览次数 */
    private Integer views;
    /** 可否赞赏 */
    private boolean appreciation;
    /** 转载声明 */
    private boolean shareStatement;
    /** 可否评论 */
    private boolean commentabled;
    /** 状态 */
    private boolean published;
    /** 是否推荐 */
    private boolean recommend;
    /** 描述 */
    private String description;
    /** 创建时间 */
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    /** 更新时间 */
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;

    @Transient
    private String tagIds;

    /** 多个博客对应一个类型 */
    @ManyToOne // 关系维护端，主动去维护Type
    private Type type;

    /** 多个博客对应多个标签 */
    @ManyToMany(cascade = {CascadeType.PERSIST})
    private List<Tag> tags = new ArrayList<>();

    /** 多个博客对应一个用户 */
    @ManyToOne // 关系维护端，主动去维护User
    private User user;

    @OneToMany(mappedBy = "blog")
    private List<Comment> comments = new ArrayList<>();

    public void init() {
        this.tagIds = tagsToIds(this.getTags());
    }

    private String tagsToIds(List<Tag> tags) {
        if (!tags.isEmpty()) {
            StringBuffer ids = new StringBuffer();
            boolean flag = false;
            for (Tag tag : tags) {
                if (flag) {
                    ids.append(",");
                } else {
                    flag = true;
                }
                ids.append(tag.getId());
            }
            return ids.toString();
        } else {
            return tagIds;
        }
    }
}
