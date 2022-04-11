package com.xxxx.server.controller;


import com.xxxx.server.pojo.Admin;
import com.xxxx.server.pojo.RespBean;
import com.xxxx.server.service.IAdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author fangyu
 * @since 2022-03-14
 */
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private IAdminService adminService;

    @ApiOperation(value = "添加管理员信息")
    @PostMapping("/")
    public RespBean addAdmin(@RequestBody Admin admin){
        if(adminService.save(admin)){
            return RespBean.success("添加成功！");
        }
        return RespBean.error("添加失败！");
    }

    @ApiOperation(value = "获取所有管理员")
    @GetMapping("/")
    public List<Admin> getAllAdmin(){
        return adminService.list();
    }

    @ApiOperation(value = "更新管理员信息")
    @PutMapping("/")
    public RespBean updateAdmin(@RequestBody Admin admin){
        if(adminService.updateById(admin)){
            return RespBean.success("更新成功!");
        }
        return RespBean.error("更新失败!");
    }

    @ApiOperation(value = "删除管理员")
    @DeleteMapping("/{id}")
    public RespBean deleteAdmin(@PathVariable Integer id){
        if(adminService.removeById(id)){
            return RespBean.success("删除成功!");
        }
        return RespBean.error("删除失败!");
    }

    @ApiOperation(value = "批量删除管理员")
    @DeleteMapping("/")
    public RespBean deleteAdminById(String[] ids){
        if(adminService.removeByIds(Arrays.asList(ids))){
            return RespBean.success("删除成功!");
        }
        return RespBean.error("删除失败!");
    }

    @ApiOperation(value = "更新管理员密码")
    @PutMapping("/pass")
    public RespBean updateAdminPassword(@RequestBody Map<String,Object> info){
        String oldPass=(String) info.get("oldPass");
        String newPass =(String) info.get("newPass");
        Integer id=(Integer) info.get("id");
        System.out.println(info);
        return adminService.updateAdminPassword(oldPass,newPass,id);
    }



}
