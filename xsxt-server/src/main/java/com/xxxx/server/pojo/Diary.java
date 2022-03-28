package com.xxxx.server.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author zhoubin
 * @since 2022-03-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_diary")
@ApiModel(value="Diary对象", description="")
public class Diary implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "日记编号")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "课题编号")
    private Integer topicId;

    @ApiModelProperty(value = "日记文件名称")
    private String title;

    @ApiModelProperty(value = "日记文件位置")
    private String location;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;


}
