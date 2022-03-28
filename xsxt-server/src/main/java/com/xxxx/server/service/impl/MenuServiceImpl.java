package com.xxxx.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xxxx.server.mapper.MenuMapper;
import com.xxxx.server.pojo.Admin;
import com.xxxx.server.pojo.Menu;
import com.xxxx.server.pojo.Student;
import com.xxxx.server.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhoubin
 * @since 2022-03-17
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {
    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<Menu> getMenusByAdminPermission() {

        int permission=0;
        //检测通过java.class().toString()对比
        if(SecurityContextHolder.getContext().getAuthentication().getPrincipal().getClass().toString().equals("class com.xxxx.server.pojo.Student")){
            permission=2;
        }
        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal().getClass().toString().equals("class com.xxxx.server.pojo.Teacher")){
            permission=1;
        }
        System.out.println(permission);
        return menuMapper.getMenusByAdminPermission(permission);

    }
}
