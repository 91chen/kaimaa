package cn.harveychan.kaimaa.service;

import cn.harveychan.kaimaa.po.Comment;

import java.util.List;

public interface CommentService {

    public List<Comment> listCommentByBlogId(Long blogId);

    public Comment saveComment(Comment comment);
}
