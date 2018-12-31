package cn.harveychan.kaimaa.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BlogQuery {

    /** 标题 */
    private String title;
    /** 分类ID */
    private Long typeId;
    /** 是否推荐 */
    private boolean recommend;
}
