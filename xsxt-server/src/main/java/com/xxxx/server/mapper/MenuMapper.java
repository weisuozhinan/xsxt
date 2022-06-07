package com.xxxx.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xxxx.server.pojo.Menu;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author fangyu
 * @since 2022-03-17
 */
public interface MenuMapper extends BaseMapper<Menu> {
    /**
     *
     * @param permission
     * @return
     */
    List<Menu> getMenusByAdminPermission(Integer permission);
}
