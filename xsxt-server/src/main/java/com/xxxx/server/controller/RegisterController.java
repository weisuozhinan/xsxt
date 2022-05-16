package com.xxxx.server.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xxxx.server.mapper.AdminMapper;
import com.xxxx.server.mapper.StudentMapper;
import com.xxxx.server.mapper.TeacherMapper;
import com.xxxx.server.pojo.*;
import com.xxxx.server.service.IAdminService;
import com.xxxx.server.service.IStudentService;
import com.xxxx.server.service.ITeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Api(tags = "RegisterController")
@RestController

public class RegisterController {

    @Autowired
    private IAdminService adminService;

    @Autowired
    private IStudentService studentService;

    @Autowired
    private ITeacherService teacherService;

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private TeacherMapper teacherMapper;

    @Autowired
    private StudentMapper studentMapper;

    @ApiOperation(value = "管理员注册")
    @PostMapping("/register/admin")
    public RespBean registerAdmin(@RequestBody Admin admin) {
        if (adminMapper.selectOne(new QueryWrapper<Admin>().eq("username", admin.getUsername())) == null) {
            adminService.save(admin);
            return RespBean.success("注册成功");
        } else {
            return RespBean.error("该用户已存在！");
        }
    }

    @ApiOperation(value = "教师注册")
    @PostMapping("/register/teacher")
    public RespBean registerTeacher(@RequestBody Teacher teacher) {
        if (teacherMapper.selectOne(new QueryWrapper<Teacher>().eq("username", teacher.getUsername())) == null) {
            teacherService.save(teacher);
            return RespBean.success("注册成功");
        } else {
            return RespBean.error("该用户已存在！");
        }
    }

    @ApiOperation(value = "学生注册")
    @PostMapping("/register/student")
    public RespBean registerStudent(@RequestBody Student student) {
        if (studentMapper.selectOne(new QueryWrapper<Student>().eq("username", student.getUsername())) == null) {
            studentService.save(student);
            return RespBean.success("注册成功");
        } else {
            return RespBean.error("该用户已存在！");
        }
    }

}
