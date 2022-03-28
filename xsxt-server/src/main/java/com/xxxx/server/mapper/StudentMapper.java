package com.xxxx.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xxxx.server.pojo.Student;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zhoubin
 * @since 2022-03-14
 */
public interface StudentMapper extends BaseMapper<Student> {
    /**
     * 获取所有学生(分页)
     * @param page
     * @param student
     * @return
     */
    IPage<Student> getStudentByPage(Page<Student> page, @Param("student") Student student);
}
