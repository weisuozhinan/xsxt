package com.xxxx.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xxxx.server.pojo.RespBean;
import com.xxxx.server.pojo.RespPageBean;
import com.xxxx.server.pojo.Teacher;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhoubin
 * @since 2022-03-14
 */
public interface ITeacherService extends IService<Teacher> {
    /**
     * 登陆之后返回token
     * @param username
     * @param password
     * @param code
     * @param request
     * @return
     */
    RespBean login(String username, String password, String code, HttpServletRequest request);

    /**
     * 根据用户名获取用户
     * @param username
     * @return
     */
    Teacher getTeacherByUserName(String username);

    /**
     * 更新教师密码
     * @param oldPass
     * @param newPass
     * @param id
     * @return
     */
    RespBean updateTeacherPassword(String oldPass, String newPass, Integer id);

    /**
     * 获取教师分页
     * @param currentPage
     * @param size
     * @param teacher
     * @return
     */
    RespPageBean getTeacherByPage(Integer currentPage, Integer size, Teacher teacher);
}
