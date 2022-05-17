package com.xxxx.server.controller;


import com.xxxx.server.pojo.RespBean;
import com.xxxx.server.pojo.RespPageBean;
import com.xxxx.server.pojo.Teacher;
import com.xxxx.server.service.ISubjectService;
import com.xxxx.server.service.ITeacherService;
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
@RequestMapping("/teacher")
public class TeacherController {
    @Autowired
    private ITeacherService teacherService;

    @Autowired
    private ISubjectService subjectService;

    @ApiOperation(value = "添加教师信息")
    @PostMapping("/")
    public RespBean addStudent(@RequestBody Teacher teacher){
        if(teacherService.save(teacher)){
            return RespBean.success("添加成功！");
        }
        return RespBean.error("添加失败！");
    }

    /**
     * 获取教师分页
     * @param currentPage
     * @param size
     * @param teacher
     * @return
     */
    @ApiOperation(value = "获取所有教师(分页)")
    @GetMapping("/")
    public RespPageBean getAllTeacher(@RequestParam(defaultValue = "1") Integer currentPage,
                                      @RequestParam(defaultValue = "10")Integer size,
                                      Teacher teacher){
        return teacherService.getTeacherByPage(currentPage,size,teacher);

    }


    @ApiOperation(value = "更新教师信息")
    @PutMapping("/")
    public RespBean updateTeacher(@RequestBody Teacher teacher){
        if(teacherService.updateById(teacher)){
            return RespBean.success("更新成功!");
        }
        return RespBean.error("更新失败!");
    }

    @ApiOperation(value = "删除教师")
    @DeleteMapping("/{id}")
    public RespBean deleteTeacher(@PathVariable Integer id){
        if(subjectService.getById(id)==null) {
            if (teacherService.removeById(id)) {
                return RespBean.success("删除成功!");
            }
        }
        return RespBean.error("删除失败,该教师有正在进行中的课程!");
    }

    @ApiOperation(value = "批量删除教师")
    @DeleteMapping("/")
    public RespBean deleteTeacherById(String[] ids){
        if(teacherService.removeByIds(Arrays.asList(ids))){
            return RespBean.success("删除成功!");
        }
        return RespBean.error("删除失败!");
    }

    @ApiOperation(value = "更新教师密码")
    @PutMapping("/pass")
    public RespBean updateTeacherPassword(@RequestBody Map<String,Object> info){
        String oldPass=(String) info.get("oldPass");
        String newPass =(String) info.get("newPass");
        Integer id=(Integer) info.get("id");
        System.out.println(info);
        return teacherService.updateTeacherPassword(oldPass,newPass,id);
    }

}
