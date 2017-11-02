package yc.controller;

import yc.bean.blog.Blog;
import yc.service.BlogService;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by song9 on 2017/1/13.
 */
@Controller
@RequestMapping("/blog")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @RequiresRoles("blogger")
    @RequestMapping("/write")
    public String write() {
        return "blog/write";
    }

    @RequiresRoles("blogger")
    @RequestMapping(value = "/submit",method = RequestMethod.POST)
    public String submit(Blog blog, Model model) {
        blogService.insertBlog(blog);
        //blogService.getBlogByTitle(blog.getTitle());
        //blogService.updateAlterTime(blog.getId(),blog);
        return "redirect:/";
    }



}
