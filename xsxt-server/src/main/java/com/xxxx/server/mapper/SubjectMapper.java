package com.xxxx.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xxxx.server.pojo.Subject;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author fangyu
 * @since 2022-03-20
 */
public interface SubjectMapper extends BaseMapper<Subject> {

    /**
     * 获取所有课题（分页）
     * @param page
     * @param subject
     * @return
     */
    IPage<Subject> getSubjectByPageByAdmin(Page<Subject> page, @Param("subject") Subject subject,
                                  @Param("createDateScope") LocalDate[] createDateScope,
                                  @Param("openDateScope") LocalDate[] openDateScope);
    /**
     * 获取所有课题（分页）
     * @param page
     * @param subject
     * @return
     */
    IPage<Subject> getSubjectByPageByTeacher(Page<Subject> page, @Param("subject") Subject subject,
                                  @Param("createDateScope") LocalDate[] createDateScope,
                                  @Param("openDateScope") LocalDate[] openDateScope);
    /**
     * 获取所有课题（分页）
     * @param page
     * @param subject
     * @return
     */
    IPage<Subject> getSubjectByPageByStudent(Page<Subject> page, @Param("subject") Subject subject,
                                  @Param("createDateScope") LocalDate[] createDateScope,
                                  @Param("openDateScope") LocalDate[] openDateScope);

    /**
     * 获取所有课题（分页）
     * @param page
     * @param subject
     * @return
     */
    IPage<Subject> getSubjectByPageByStudentN(Page<Subject> page, @Param("subject") Subject subject,
                                  @Param("createDateScope") LocalDate[] createDateScope,
                                  @Param("openDateScope") LocalDate[] openDateScope);

    /**
     *
     * @param studentId
     * @return
     */
    List<Subject> isHaveSubjectByStudentId(Integer studentId);

    /**
     * 教师获取开题文件分页
     * @param page
     * @param subject
     * @param createDateScope
     * @return
     */
    IPage<Subject> getProposalByPageByTeacher(Page<Subject> page,@Param("subject")  Subject subject,
                                              @Param("createDateScope") LocalDate[] createDateScope);
}
