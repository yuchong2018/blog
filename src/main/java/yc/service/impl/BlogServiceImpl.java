package yc.service.impl;

import yc.mapper.blog.BlogMapper;
import yc.bean.blog.Blog;
import yc.bean.blog.BlogExample;
import yc.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by song9 on 2017/1/13.
 */
@Service
public class BlogServiceImpl implements BlogService {

    @Autowired(required = false)
    private BlogMapper blogMapper;

    @Override
    public void insertBlog(Blog blog) {
        blog.setCreatetime(new Date());
        blog.setAltertime(new Date());
        blogMapper.insert(blog);
    }

    @Override
    public void updateAlterTime(Integer id, Blog blog) {
    }

    @Override
    public Blog getBlogById(Integer id) {
        blogMapper.selectByPrimaryKey(id);
        return blogMapper.selectByPrimaryKey(id);
    }

    @Override
    public Blog getBlogByTitle(String title) {
        return null;
    }

    @Override
    public List<Blog> getAllBlog() {
        BlogExample blogExample = new BlogExample();
        List<Blog> blogs = blogMapper.selectByExampleWithBLOBs(blogExample);
        return blogs;
    }
}
