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
 * 评论
 */
@Data
@NoArgsConstructor
@ToString
@Entity
@Table(name = "comment")
public class Comment implements Serializable {

    /** 主键ID */
    @Id
    private Long id;
    /** 昵称 */
    private String nickname;
    /** 邮箱 */
    private String email;
    /** 头像 */
    private String avatar;
    /** 内容 */
    private String content;
    /** 创建时间 */
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    @ManyToOne
    private Blog blog;

    @OneToMany(mappedBy = "parentComment")
    private List<Comment> replyComments = new ArrayList<>();

    @ManyToOne
    private Comment parentComment;

    private boolean adminComment;
}
