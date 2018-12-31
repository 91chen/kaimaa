package cn.harveychan.kaimaa.service.impl;

import cn.harveychan.kaimaa.dao.CommentRepository;
import cn.harveychan.kaimaa.po.Comment;
import cn.harveychan.kaimaa.util.SnowflakeIdWorker;
import cn.harveychan.kaimaa.service.CommentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private SnowflakeIdWorker idWorker;

    private List<Comment> tempReplys = new ArrayList<>();

    @Override
    public List<Comment> listCommentByBlogId(Long blogId) {
        Sort sort = new Sort("createTime");
        List<Comment> comments = commentRepository.findByBlogIdAndParentCommentNull(blogId, sort);
        return eachComment(comments);
    }

    /**
     * 循环每个顶级的评论节点
     *
     * @param comments
     * @return
     */
    private List<Comment> eachComment(List<Comment> comments) {
        List<Comment> commentList = new ArrayList<>();
        for (Comment comment : comments) {
            Comment c = new Comment();
            BeanUtils.copyProperties(comment, c);
            commentList.add(c);
        }
        // 合并评论的各层子代到第一级子代集合中
        combineChildren(commentList);
        return commentList;
    }

    private void combineChildren(List<Comment> comments) {
        for (Comment comment : comments) {
            List<Comment> replys = comment.getReplyComments();
            for (Comment reply : replys) {
                recursively(reply);
            }
            comment.setReplyComments(tempReplys);
            // 清除临时存放区
            tempReplys = new ArrayList<>();
        }
    }

    private void recursively(Comment comment) {
        tempReplys.add(comment);
        if (comment.getReplyComments().size() > 0) {
            List<Comment> replys = comment.getReplyComments();
            for (Comment reply : replys) {
                tempReplys.add(reply);
                if (reply.getReplyComments().size() > 0) {
                    recursively(reply);
                }
            }
        }
    }

    @Transactional
    @Override
    public Comment saveComment(Comment comment) {
        Long parentCommentId = comment.getParentComment().getId();
        if (parentCommentId != -1) {
            comment.setParentComment(commentRepository.findById(parentCommentId).orElse(null));
        }  else {
            comment.setParentComment(null);
        }
        comment.setId(idWorker.nextId());
        comment.setCreateTime(new Date());
        return commentRepository.save(comment);
    }
}
