package com.xxxx.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xxxx.server.config.security.component.JwtTokenUtil;
import com.xxxx.server.mapper.AdminMapper;
import com.xxxx.server.pojo.Admin;
import com.xxxx.server.pojo.RespBean;
import com.xxxx.server.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhoubin
 * @since 2022-03-14
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements IAdminService {

    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Override
    public RespBean login( String username, String password, String code, HttpServletRequest request) {
        //验证
        String captcha=(String)request.getSession().getAttribute("captcha");
        if(StringUtils.isEmpty(code)||!captcha.equalsIgnoreCase(code)){
            return RespBean.error("验证码输入错误，请重新输入！");
        }
        //登录
        UserDetails userDetails =userDetailsService.loadUserByUsername(username);
        if(null==userDetails||!password.equals(userDetails.getPassword())|| adminMapper.selectOne(new QueryWrapper<Admin>().eq("username",userDetails.getUsername()).eq("password",userDetails.getPassword()))==null
        ){
            return RespBean.error("用户名或密码不正确");
        }
        //更新security登录用户对象
        UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        String token=jwtTokenUtil.generateToken(userDetails);
        Map<String,String> tokenMap=new HashMap<>();
        tokenMap.put("token",token);
        tokenMap.put("tokenHead",tokenHead);

        return RespBean.success("登录成功",tokenMap);
    }

    @Override
    public RespBean register(String username, String password, String name, String gender, String phone, int permission, HttpServletRequest request) {



        return null;
    }

    /**
     * 根据用户名获取密码
     * @param username
     * @return
     */
    @Override
    public Admin getAdminByUserName(String username) {
        return adminMapper.selectOne(new QueryWrapper<Admin>().eq("username",username));
                //.eq("enabled",true));//判断用户名是否正确，用户是否被禁用
    }

    @Override
    public RespBean updateAdminPassword(String oldPass, String newPass, Integer id) {
        Admin admin =adminMapper.selectById(id);
        //判断密码是否正确
        if(admin.getPassword().equals(oldPass)){
            admin.setPassword(newPass);
            int result =adminMapper.updateById(admin);
            if(1==result){
                return RespBean.success("更新密码成功！");

            }
        }
        return RespBean.error("更新密码失败！");
    }

}
