package com.xxxx.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xxxx.server.pojo.RespBean;
import com.xxxx.server.pojo.RespPageBean;
import com.xxxx.server.pojo.Student;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhoubin
 * @since 2022-03-14
 */
public interface IStudentService extends IService<Student> {
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
    Student getStudentByUserName(String username);

    /**
     * 更新学生密码
     * @param oldPass
     * @param newPass
     * @param id
     * @return
     */
    RespBean updateStudentPassword(String oldPass, String newPass, Integer id);

    /**获取所有学生分页
     * @param currentPage
     * @param size
     * @param student
     * @return
     */
    RespPageBean getStudentByPage(Integer currentPage, Integer size, Student student);
    
}
