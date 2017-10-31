package yc.controller;

import yc.po.blog.Blog;
import yc.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by song9 on 2017/1/13.
 */
@Controller

public class HomeController {

    @Autowired
    private BlogService blogService;

    @RequestMapping("/home")
    public String home(Model model) {
        List<Blog> blogs = blogService.getAllBlog();
        model.addAttribute("blogs", blogs);
        return "home";
    }

    @ResponseBody
    @RequestMapping("/getblog")
    public List<Blog> getblog(Blog blog) {
        List<Blog> blogs = blogService.getAllBlog();
        return blogs;
    }




}
