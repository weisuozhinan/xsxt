package com.xxxx.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xxxx.server.pojo.Teacher;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zhoubin
 * @since 2022-03-14
 */
public interface TeacherMapper extends BaseMapper<Teacher> {
    /**
     * 获取所有教师(分页)
     * @param page
     * @param teacher
     * @return
     */
    IPage<Teacher> getTeacherByPage(Page<Teacher> page,@Param("teacher") Teacher teacher);
}
