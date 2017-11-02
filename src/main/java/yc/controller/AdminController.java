package yc.controller;

import yc.bean.user.*;
import yc.service.UserService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;
import java.util.List;

/**
 * Created by song9 on 2017/1/4.
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private SessionDAO sessionDAO;

    @RequestMapping
    private String admin() {
        return "admin/home";
    }

    //查看注册用户
    @RequiresRoles(value={"blogger","administrator"},logical = Logical.OR)
    @RequestMapping("/checkUserList")
    public String checkUserList(UserQueryVo userQueryVo, Model model) {
        List<UserCustom> userList = userService.getAllUser();
       // List<UserCustom> userList = userService.getUserList(userQueryVo);
        model.addAttribute("userList", userList);
        return "admin/userList";
    }

    //查看正在登录的用户
    @RequiresRoles(value={"blogger","administrator"},logical = Logical.OR)
    @RequestMapping("/checkSessionList")
    public String checkSessionList() {
        Collection<Session> sessions = sessionDAO.getActiveSessions();
        System.out.println(sessions.size());
        return null;
    }

}
