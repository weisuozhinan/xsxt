package com.xxxx.server.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xxxx.server.config.security.component.JwtTokenUtil;
import com.xxxx.server.mapper.FileMapper;
import com.xxxx.server.mapper.SubjectMapper;
import com.xxxx.server.pojo.RespBean;
import com.xxxx.server.pojo.RespPageBean;
import com.xxxx.server.pojo.Subject;
import com.xxxx.server.service.IFileService;
import com.xxxx.server.service.ISubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author fangyu
 * @since 2022-03-20
 */
@Service
public class SubjectServiceImpl extends ServiceImpl<SubjectMapper, Subject> implements ISubjectService {

    @Autowired
    private SubjectMapper subjectMapper;
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private FileMapper fileMapper;

    @Autowired
    private IFileService fileService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Value("${jwt.tokenHead}")
    private String tokenHead;




    /**
     * 获取所有课题分页
     * @param currentPage
     * @param size
     * @param subject
     * @param createDateScope
     * @param openDateScope
     * @return
     */
    @Override
    public RespPageBean getSubjectByPageByAdmin(Integer currentPage, Integer size, Subject subject, LocalDate[] createDateScope, LocalDate[] openDateScope) {

        Page<Subject> page = new Page<>(currentPage, size);
        IPage<Subject> subjectByPageByAdmin = subjectMapper.getSubjectByPageByAdmin(page, subject,createDateScope,openDateScope);
        RespPageBean respPageBean=new RespPageBean(subjectByPageByAdmin.getTotal(),subjectByPageByAdmin.getRecords());
        return respPageBean;
    }
    /**
     * 获取所有课题分页
     * @param currentPage
     * @param size
     * @param subject
     * @param createDateScope
     * @param openDateScope
     * @return
     */
    @Override
    public RespPageBean getSubjectByPageByTeacher(Integer currentPage, Integer size, Subject subject, LocalDate[] createDateScope, LocalDate[] openDateScope) {

        Page<Subject> page = new Page<>(currentPage, size);
        IPage<Subject> subjectByPageByTeacher = subjectMapper.getSubjectByPageByTeacher(page, subject,createDateScope,openDateScope);
        RespPageBean respPageBean=new RespPageBean(subjectByPageByTeacher.getTotal(),subjectByPageByTeacher.getRecords());
        return respPageBean;
    }
    /**
     * 获取所有课题分页
     * @param currentPage
     * @param size
     * @param subject
     * @param createDateScope
     * @param openDateScope
     * @return
     */
    @Override
    public RespPageBean getSubjectByPageByStudent(Integer currentPage, Integer size, Subject subject, LocalDate[] createDateScope, LocalDate[] openDateScope) {

        Page<Subject> page = new Page<>(currentPage, size);
        IPage<Subject> subjectByPageByStudent = subjectMapper.getSubjectByPageByStudent(page, subject,createDateScope,openDateScope);
        RespPageBean respPageBean=new RespPageBean(subjectByPageByStudent.getTotal(),subjectByPageByStudent.getRecords());
        return respPageBean;
    }

    /**
     * 获取所有课题分页
     * @param currentPage
     * @param size
     * @param subject
     * @param createDateScope
     * @param openDateScope
     * @return
     */
    @Override
    public RespPageBean getSubjectByPageByStudentN(Integer currentPage, Integer size, Subject subject, LocalDate[] createDateScope, LocalDate[] openDateScope) {

        Page<Subject> page = new Page<>(currentPage, size);
        IPage<Subject> subjectByPageByStudentN = subjectMapper.getSubjectByPageByStudentN(page, subject,createDateScope,openDateScope);
        RespPageBean respPageBean=new RespPageBean(subjectByPageByStudentN.getTotal(),subjectByPageByStudentN.getRecords());
        return respPageBean;
    }

    @Override
    public List<Subject> isHaveSubjectByStudentId(Integer studentId){
        return subjectMapper.isHaveSubjectByStudentId(studentId);
    }

    /**
     *教师获取开题文件分页
     * @param currentPage
     * @param size
     * @param subject
     * @param createDateScope
     * @return
     */
    @Override
    public RespPageBean getProposalFileByPageByTeacher(Integer currentPage, Integer size, Subject subject, LocalDate[] createDateScope) {
        Page<Subject> page = new Page<>(currentPage, size);
        IPage<Subject> proposalByPageByTeacher = subjectMapper.getProposalByPageByTeacher(page, subject,createDateScope);
        RespPageBean respPageBean=new RespPageBean(proposalByPageByTeacher.getTotal(),proposalByPageByTeacher.getRecords());
        return respPageBean;
    }


}
