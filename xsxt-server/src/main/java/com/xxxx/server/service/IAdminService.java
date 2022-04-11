package com.xxxx.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xxxx.server.pojo.Admin;
import com.xxxx.server.pojo.RespBean;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhoubin
 * @since 2022-03-14
 */
public interface IAdminService extends IService<Admin> {

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
     * 登陆之后返回token
     * @param username
     * @param password
     * @param name
     * @param gender
     * @param phone
     * @param permission
     * @param request
     * @return
     */
    RespBean register(String username, String password, String name,String gender,String phone,int permission, HttpServletRequest request);

    /**
     * 根据用户名获取用户
     * @param username
     * @return
     */
    Admin getAdminByUserName(String username);

    /**
     * 更新用户密码
     * @param oldPass
     * @param newPass
     * @param adminId
     * @return
     */
    RespBean updateAdminPassword(String oldPass, String newPass, Integer adminId);


}
