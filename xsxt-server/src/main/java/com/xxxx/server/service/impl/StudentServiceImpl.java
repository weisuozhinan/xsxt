package com.xxxx.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xxxx.server.config.security.component.JwtTokenUtil;
import com.xxxx.server.mapper.AdminMapper;
import com.xxxx.server.mapper.StudentMapper;
import com.xxxx.server.pojo.Admin;
import com.xxxx.server.pojo.RespBean;
import com.xxxx.server.pojo.RespPageBean;
import com.xxxx.server.pojo.Student;
import com.xxxx.server.service.IStudentService;
import io.swagger.annotations.ApiModelProperty;
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
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author fangyu
 * @since 2022-03-14
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements IStudentService {
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Override
    public RespBean login(String username, String password, String code, HttpServletRequest request) {

        //验证
        String captcha=(String)request.getSession().getAttribute("captcha");
        if(StringUtils.isEmpty(code)||!captcha.equalsIgnoreCase(code)){
            return RespBean.error("验证码输入错误，请重新输入！");
        }
        //登录
        UserDetails userDetails =userDetailsService.loadUserByUsername(username);
        if(null==userDetails||!password.equals(userDetails.getPassword())||studentMapper.selectOne(new QueryWrapper<Student>().eq("username",userDetails.getUsername()).eq("password",userDetails.getPassword()))==null){
            System.out.println(userDetails.getPassword());
            System.out.println(password);
            return RespBean.error("用户名或密码不正确");
        }
        //更新security登录用户对象
        UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);


        System.out.println(userDetails.getPassword());
        System.out.println(userDetails.getUsername());


        String token=jwtTokenUtil.generateToken(userDetails);
        Map<String,String> tokenMap=new HashMap<>();
        tokenMap.put("token",token);
        tokenMap.put("tokenHead",tokenHead);

        return RespBean.success("登录成功",tokenMap);
    }

    /**
     * 根据用户名获取密码
     * @param username
     * @return
     */
    @Override
    public Student getStudentByUserName(String username) {
        return studentMapper.selectOne(new QueryWrapper<Student>().eq("username",username));
        //.eq("enabled",true));//判断用户名是否正确，用户是否被禁用
    }

    @Override
    public RespBean updateStudentPassword(String oldPass, String newPass, Integer id) {

        Student student =studentMapper.selectById(id);
        //判断密码是否正确
        if(student.getPassword().equals(oldPass)){
            student.setPassword(newPass);
            int result =studentMapper.updateById(student);
            if(1==result){
                return RespBean.success("更新密码成功！");

            }
        }
        return RespBean.error("更新密码失败！");
    }

    /**
     * 获取学生分页
     *
     * @param currentPage
     * @param size
     * @param student
     * @return
     */
    @Override
    public RespPageBean getStudentByPage(Integer currentPage, Integer size, Student student) {

        Page<Student> page = new Page<>(currentPage, size);
        IPage<Student> studentByPage = studentMapper.getStudentByPage(page, student);
        RespPageBean respPageBean=new RespPageBean(studentByPage.getTotal(),studentByPage.getRecords());
        return respPageBean;
    }

}
