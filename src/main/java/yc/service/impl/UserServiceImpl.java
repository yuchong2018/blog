package yc.service.impl;

import yc.mapper.user.UserCustomMapper;
import yc.mapper.user.UserMapper;
import yc.mapper.user.UserRoleMapper;
import yc.bean.user.*;
import yc.service.UserService;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by song9 on 2016/12/29.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired(required=false)
    private UserCustomMapper userCustomMapper;

    @Autowired(required=false)
    private UserMapper userMapper;

    @Autowired(required=false)
    private UserRoleMapper userRoleMapper;

    //通过用户名查询用户
    @Override
    public UserCustom getByUserName(String userName) {
        System.out.println(userName);
        return userCustomMapper.getByUserName(userName);
    }

    //通过用户名查询角色信息
    @Override
    public Set<String> getRolesByUserName(String userName) {
        return userCustomMapper.getRolesByUserName(userName);
    }

    //通过用户名查询权限信息
    @Override
    public Set<String> getPermissionsByUserName(String userName) {
        Set<String> permissions = new HashSet<String>();
        Set<String> role = userCustomMapper.getRolesByUserName(userName);
        for(String string : role) {
            for(String string2 : userCustomMapper.getPermissionsByRole(string)) {
                permissions.add(string2);
            }
        }
        return permissions;
    }

    //通过角色查询权限信息
    @Override
    public Set<String> getPermissionsByRole(String roleName) {
        return userCustomMapper.getPermissionsByRole(roleName);
    }

    //通过表单注册账号
    @Override
    public void insertUser(UserCustom userCustom) {
        String algorithmName = "md5";
        String username = userCustom.getUsername();
        String password = userCustom.getPassword();
        String salt1 = username;
        String salt2 = new SecureRandomNumberGenerator().nextBytes().toHex();
        int hashIterations = 3;
        SimpleHash hash = new SimpleHash(algorithmName, password,
                salt1 + salt2, hashIterations);
        String encodedPassword = hash.toHex();
        userCustom.setSalt(salt2);
        userCustom.setPassword(encodedPassword);
        userCustom.setCreatetime(new Date());
        userMapper.insertSelective(userCustom);
    }

    //赋予账号角色
    @Override
    public void giveRole(UserCustom userCustom, Integer role_id) {
        UserRole userRole = new UserRole();
        userRole.setUserid(userCustomMapper.getByUserName(userCustom.getUsername()).getId());
        userRole.setRoleid(role_id);
        userRoleMapper.insertSelective(userRole);
    }

    //更新账号最后登录时间
    @Override
    public void updateLoginLastTime(UserCustom userCustom,Session session) {
        userCustom.setLasttime(session.getStartTimestamp());
        userMapper.updateByPrimaryKeySelective(userCustom);
    }

    //更新账号信息
    @Override
    public void updateUser(Integer id,UserCustom userCustom) {
        userCustom.setId(id);
        userMapper.updateByPrimaryKeySelective(userCustom);
    }

    //用户查询列表 wrong
    @Override
    public List<UserCustom> getUserList(UserQueryVo userQueryVo) {
        return userCustomMapper.getUserList(userQueryVo);
    }

    @Override
    public List<UserCustom> getAllUser() {
        return userCustomMapper.getAllUser();
    }

    //@Override
    //public List<User> test(String userName) {
    //    UserExample userExample = new UserExample();
    //    userExample.or().andUserNameEqualTo(userName);
    //    return userMapper.selectByExample(userExample);
    //}
}
