package com.xxxx.server.controller;
import com.xxxx.server.pojo.*;
import com.xxxx.server.service.IAdminService;
import com.xxxx.server.service.IStudentService;
import com.xxxx.server.service.ITeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Api(tags = "LoginController")
@RestController
public class LoginController {

    @Autowired
    private IAdminService adminService;

    @Autowired
    private IStudentService studentService;

    @Autowired
    private ITeacherService teacherService;

    @ApiOperation(value ="登录之后返回token")
    @PostMapping("/login")
    public RespBean login(@RequestBody AdminLoginParam adminLoginParam, HttpServletRequest request) {
        if (adminLoginParam.getPermission() == 0) {
            System.out.println("输入权限是管理员");
            return adminService.login(adminLoginParam.getUsername(), adminLoginParam.getPassword(), adminLoginParam.getCode(), request);

        }else if(adminLoginParam.getPermission() == 2){
            System.out.println("输入权限是学生");
            return studentService.login(adminLoginParam.getUsername(), adminLoginParam.getPassword(), adminLoginParam.getCode(), request);
        }
        else {
            System.out.println("输入权限是教师");
            return teacherService.login(adminLoginParam.getUsername(), adminLoginParam.getPassword(), adminLoginParam.getCode(), request);

        }
    }

    @ApiOperation(value = "获取当前登录用户的信息")
    @GetMapping("/admin/info")
    public Admin getAdminInfo(Principal principal){
        if(null==principal){
            return null;
        }

        String username = principal.getName();
        Admin admin = adminService.getAdminByUserName(username);
        admin.setPassword(null);//不返回用户密码
       // admin.setRoles(adminService.getRoles(admin.getId()));
        System.out.println(username+"这里是admin");
        return admin;
    }

    @ApiOperation(value = "获取当前学生用户的信息")
    @GetMapping("/student/info")
    public Student getStudentInfo(Principal principal){
        if(null==principal){
            return null;
        }
        String username = principal.getName();
        Student student = studentService.getStudentByUserName(username);
        student.setPassword(null);//不返回用户密码
        // admin.setRoles(adminService.getRoles(admin.getId()));
        System.out.println(username+"这里是student");
        return student;
    }

    @ApiOperation(value = "获取当前教师用户的信息")
    @GetMapping("/teacher/info")
    public Teacher getTeacherInfo(Principal principal){
        if(null==principal){
            return null;
        }
        String username = principal.getName();
        Teacher teacher = teacherService.getTeacherByUserName(username);
        teacher.setPassword(null);//不返回用户密码
        // admin.setRoles(adminService.getRoles(admin.getId()));
        System.out.println(username+"这里是teacher");
        return teacher;
    }

    @ApiOperation(value = "退出登录")
    @PostMapping("/logout")
    public RespBean logout(){
        return RespBean.success("注销成功！");
    }

}
