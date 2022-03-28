package com.xxxx.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xxxx.server.pojo.RespBean;
import com.xxxx.server.pojo.RespPageBean;
import com.xxxx.server.pojo.Subject;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author zhoubin
 * @since 2022-03-20
 */
public interface ISubjectService extends IService<Subject> {


    /**
     * 获取所有课题分页
     *
     * @param currentPage
     * @param size
     * @param subject
     * @param createDateScope
     * @param openDateScope
     * @return
     */
    RespPageBean getSubjectByPageByAdmin(Integer currentPage, Integer size, Subject subject, LocalDate[] createDateScope, LocalDate[] openDateScope);

    /**
     * 获取所有课题分页
     *
     * @param currentPage
     * @param size
     * @param subject
     * @param createDateScope
     * @param openDateScope
     * @return
     */
    RespPageBean getSubjectByPageByTeacher(Integer currentPage, Integer size, Subject subject, LocalDate[] createDateScope, LocalDate[] openDateScope);

    /**
     * 获取所有课题分页
     *
     * @param currentPage
     * @param size
     * @param subject
     * @param createDateScope
     * @param openDateScope
     * @return
     */
    RespPageBean getSubjectByPageByStudent(Integer currentPage, Integer size, Subject subject, LocalDate[] createDateScope, LocalDate[] openDateScope);

    /**
     * 获取所有课题分页
     *
     * @param currentPage
     * @param size
     * @param subject
     * @param createDateScope
     * @param openDateScope
     * @return
     */
    RespPageBean getSubjectByPageByStudentN(Integer currentPage, Integer size, Subject subject, LocalDate[] createDateScope, LocalDate[] openDateScope);

    /**
     * @param studentId
     * @return
     */
    List<Subject> isHaveSubjectByStudentId(Integer studentId);

    /**
     * 教师获取开题文件分页
     * @param currentPage
     * @param size
     * @param subject
     * @param createDateScope
     * @return
     */
    RespPageBean getProposalFileByPageByTeacher(Integer currentPage, Integer size, Subject subject, LocalDate[] createDateScope);

}
