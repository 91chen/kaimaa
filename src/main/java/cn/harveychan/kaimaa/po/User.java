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
 * 用户
 */
@Data
@NoArgsConstructor
@ToString
@Entity
@Table(name = "user")
public class User implements Serializable {

    /** 主键ID */
    @Id
    private Long id;
    /** 昵称 */
    private String nickname;
    /** 用户名 */
    private String username;
    /** 密码 */
    private String password;
    /** 手机 */
    private String phone;
    /** 邮箱 */
    private String email;
    /** 头像 */
    private String avatar;
    /** 类型 */
    private Integer type;
    /** 创建时间 */
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    /** 更新时间 */
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;

    @OneToMany(mappedBy = "user")
    private List<Blog> blogs = new ArrayList<>();
}
