package cn.harveychan.kaimaa.service;

import cn.harveychan.kaimaa.po.Blog;
import cn.harveychan.kaimaa.vo.BlogQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface BlogService {

    public Blog getBlog(Long id);

    public Blog getAndConvert(Long id);

    public Page<Blog> listBlog(Pageable pageable);

    public Page<Blog> listBlog(Pageable pageable, BlogQuery blog);

    public Page<Blog> listBlog(Pageable pageable, String query);

    public Page<Blog> listBlog(Pageable pageable, Long tagId);

    public List<Blog> listRecommendBlogTop(Integer size);

    public Map<String, List<Blog>> archiveBlog();

    public Long countBlog();

    public Blog saveBlog(Blog blog);

    public Blog updateBlog(Long id, Blog blog);

    public void deleteBlog(Long id);
}
