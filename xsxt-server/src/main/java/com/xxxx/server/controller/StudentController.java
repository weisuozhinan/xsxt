package com.xxxx.server.controller;


import com.xxxx.server.pojo.RespBean;
import com.xxxx.server.pojo.RespPageBean;
import com.xxxx.server.pojo.Student;
import com.xxxx.server.service.IStudentService;
import com.xxxx.server.service.ISubjectService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhoubin
 * @since 2022-03-14
 */
@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private IStudentService studentService;

    @Autowired
    private ISubjectService subjectService;

    @ApiOperation(value = "添加学生信息")
    @PostMapping("/")
    public RespBean addStudent(@RequestBody Student student){
        if(studentService.save(student)){
            return RespBean.success("添加成功！");
        }
        return RespBean.error("添加失败！");
    }


    /**
     * 获取学生分页
     * @param currentPage
     * @param size
     * @param student
     * @return
     */
    @ApiOperation(value = "获取所有学生(分页)")
    @GetMapping("/")
    public RespPageBean getAllStudent(@RequestParam(defaultValue = "1") Integer currentPage,
                                      @RequestParam(defaultValue = "10")Integer size,
                                      Student student){
        return studentService.getStudentByPage(currentPage,size,student);

    }


    @ApiOperation(value = "更新学生信息")
    @PutMapping("/")
    public RespBean updateStudent(@RequestBody Student student){
        if(studentService.updateById(student)){
            return RespBean.success("更新成功!");
        }
        return RespBean.error("更新失败!");
    }

    @ApiOperation(value = "删除学生")
    @DeleteMapping("/{id}")
    public RespBean deleteStudent(@PathVariable Integer id){
        if(subjectService.getById(id)==null) {
            if (studentService.removeById(id)) {
                return RespBean.success("删除成功!");
            }
        }
        return RespBean.error("删除失败，该学生有正在进行中的课程!");
    }

    @ApiOperation(value = "批量删除学生")
    @DeleteMapping("/")
    public RespBean deleteStudentById(String[] ids){
        if(studentService.removeByIds(Arrays.asList(ids))){
            return RespBean.success("删除成功!");
        }
        return RespBean.error("删除失败!");
    }

    @ApiOperation(value = "更新学生密码")
    @PutMapping("/pass")
    public RespBean updateAdminPassword(@RequestBody Map<String,Object> info){
        String oldPass=(String) info.get("oldPass");
        String newPass =(String) info.get("newPass");
        Integer id=(Integer) info.get("id");
        System.out.println(info);
        return studentService.updateStudentPassword(oldPass,newPass,id);
    }
}
