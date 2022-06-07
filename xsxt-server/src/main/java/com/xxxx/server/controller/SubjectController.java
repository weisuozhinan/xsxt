package com.xxxx.server.controller;


import com.xxxx.server.pojo.*;
import com.xxxx.server.service.IFileService;
import com.xxxx.server.service.IStudentService;
import com.xxxx.server.service.ISubjectService;
import com.xxxx.server.service.ITeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author fangyu
 * @since 2022-03-20
 */
@RestController
@RequestMapping("/subject")
public class SubjectController {

    @Autowired
    private ISubjectService subjectService;

    @Autowired
    private ITeacherService teacherService;

    @Autowired
    private IStudentService studentService;

    @Autowired
    private IFileService fileService;




    /**
     * 获取课题分页
     */
    @ApiOperation(value = "管理员获取所有课题信息(分页)")
    @GetMapping("/admin/")
    public RespPageBean getAllSubjectByAdmin(@RequestParam(defaultValue = "1") Integer currentPage,
                                      @RequestParam(defaultValue = "10") Integer size,
                                      Subject subject,
                                      LocalDate[] createDateScope,
                                      LocalDate[] openDateScope) {
        return subjectService.getSubjectByPageByAdmin(currentPage, size, subject, createDateScope, openDateScope);

    }

    /**
     * 获取课题分页
     */
    @ApiOperation(value = "教师获取所有课题信息(分页)")
    @GetMapping("/teacher/")
    public RespPageBean getAllSubjectByTeacher(@RequestParam(defaultValue = "1") Integer currentPage,
                                      @RequestParam(defaultValue = "10") Integer size,
                                      Subject subject,
                                      LocalDate[] createDateScope,
                                      LocalDate[] openDateScope) {
        return subjectService.getSubjectByPageByTeacher(currentPage, size, subject, createDateScope, openDateScope);

    }

    /**
     * 教师获取开题文件分页
     */
    @ApiOperation(value = "教师获取开题文件信息(分页)")
    @GetMapping("/teacher/proposalFile/")
    public RespPageBean getAllProposalFileByTeacher(@RequestParam(defaultValue = "1") Integer currentPage,
                                               @RequestParam(defaultValue = "10") Integer size,
                                               Subject subject,
                                               LocalDate[] createDateScope) {
        return subjectService.getProposalFileByPageByTeacher(currentPage, size, subject, createDateScope);

    }


    /**
     * 获取课题分页
     */
    @ApiOperation(value = "有课学生获取所有课题信息(分页)")
    @GetMapping("/student/y/")
    public RespPageBean getAllSubjectByStudent(@RequestParam(defaultValue = "1") Integer currentPage,
                                      @RequestParam(defaultValue = "10") Integer size,
                                      Subject subject,
                                      LocalDate[] createDateScope,
                                      LocalDate[] openDateScope) {
        return subjectService.getSubjectByPageByStudent(currentPage, size, subject, createDateScope, openDateScope);

    }
    /**
     * 获取课题分页
     */
    @ApiOperation(value = "无课学生获取所有课题信息(分页)")
    @GetMapping("/student/n/")
    public RespPageBean getAllSubjectByStudentN(@RequestParam(defaultValue = "1") Integer currentPage,
                                      @RequestParam(defaultValue = "10") Integer size,
                                      Subject subject,
                                      LocalDate[] createDateScope,
                                      LocalDate[] openDateScope) {
        return subjectService.getSubjectByPageByStudentN(currentPage, size, subject, createDateScope, openDateScope);

    }




    @ApiOperation(value = "获取所有教师")
    @GetMapping("/teachers")
    public List<Teacher> getAllTeachers() {
        return teacherService.list();
    }

    @ApiOperation(value = "获取所有学生")
    @GetMapping("/students")
    public List<Student> getAllStudents() {
        return studentService.list();
    }


    @ApiOperation(value = "添加课题")
    @PostMapping("/")
    public RespBean addSubject(@RequestBody Subject subject){
        subject.setCreateDate(LocalDate.now());
        subject.setStatus("未开题");
        if(subjectService.save(subject)){
            File file=new File();
            file.setId(subject.getId());
            file.setScore(0);
            fileService.save(file);
            return RespBean.success("添加成功！");
        }
        return RespBean.error("添加失败！");
    }

    @ApiOperation(value="更新课题")
    @PutMapping("/")
    public RespBean updateSubject(@RequestBody Subject subject){
        if(subjectService.updateById(subject)){
            return RespBean.success("更新成功!");
        }
        return RespBean.error("更新失败!");
    }

    @ApiOperation(value="管理员更新课题为开题")
    @PutMapping("/updateByAdmin/")
    public RespBean updateSubjectByAdmin(@RequestBody Subject subject){
        subject.setOpenDate(LocalDate.now());
        if(subjectService.updateById(subject)){
            return RespBean.success("更新成功!");
        }
        return RespBean.error("更新失败!");
    }


    @ApiOperation(value = "删除课题")
    @DeleteMapping("/{id}")
    public RespBean deleteSubject(@PathVariable Integer id){
        if(subjectService.removeById(id)){
            fileService.removeById(id);
            return RespBean.success("删除成功!");
        }
        return RespBean.error("删除失败!");
    }

    @ApiOperation(value = "批量删除课题")
    @DeleteMapping("/")
    public RespBean deleteSubjectById(String[] ids){
        if(subjectService.removeByIds(Arrays.asList(ids))){
            return RespBean.success("删除成功!");
        }
        return RespBean.error("删除失败!");
    }


    @ApiOperation(value = "通过studentId获取学生进行中的课题")
    @GetMapping("/students/{studentId}")
    public List<Subject> isHaveSubjectByStudentId(@PathVariable Integer studentId) {

        return subjectService.isHaveSubjectByStudentId(studentId);
    }

    @ApiOperation(value = "学生退选课题")
    @DeleteMapping("/deleteByStudent/{id}")
    public RespBean deleteSubjectByStudent(@PathVariable Integer id){
        Subject subject=subjectService.getById(id);
        File file=fileService.getById(id);
        subject.setStatus("未开题");
        subject.setStudentId("");
        file.setScore(0);
        file.setProposalLocation("");
        file.setProposalTitle("");
        file.setProposalScore(0);
        file.setDemandLocation("");
        file.setDemandTitle("");
        file.setDemandScore(0);
        file.setDiaryLocation("");
        file.setDiaryTitle("");
        file.setDiaryScore(0);
        file.setCodeLocation("");
        file.setCodeTitle("");
        file.setCodeScore(0);
        file.setSummaryLocation("");
        file.setSummaryTitle("");
        file.setSummaryScore(0);
        fileService.updateById(file);
        subjectService.updateById(subject);
        return RespBean.success("退选成功！");
    }



}
