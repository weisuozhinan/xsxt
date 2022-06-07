package com.xxxx.server.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author fangyu
 * @since 2022-03-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_file")
@ApiModel(value="File对象", description="")
public class File implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "课题编号")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "课题内容名称")
    private String contentTitle;

    @ApiModelProperty(value = "课题内容位置")
    private String contentLocation;

    @ApiModelProperty(value = "课题内容创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "Asia/Shanghai")
    private LocalDate contentCreateTime;

    @ApiModelProperty(value = "开题报告名称")
    private String proposalTitle;

    @ApiModelProperty(value = "开题报告位置")
    private String proposalLocation;

    @ApiModelProperty(value = "开题报告创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "Asia/Shanghai")
    private LocalDate proposalCreateTime;

    @ApiModelProperty(value = "开题报告成绩")
    private Integer proposalScore;

    @ApiModelProperty(value = "需求分析名称")
    private String demandTitle;

    @ApiModelProperty(value = "需求分析位置")
    private String demandLocation;

    @ApiModelProperty(value = "需求分析创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "Asia/Shanghai")
    private LocalDate demandCreateTime;

    @ApiModelProperty(value = "需求分析成绩")
    private Integer demandScore;

    @ApiModelProperty(value = "日记名称")
    private String diaryTitle;

    @ApiModelProperty(value = "日记位置")
    private String diaryLocation;

    @ApiModelProperty(value = "日记创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "Asia/Shanghai")
    private LocalDate diaryCreateTime;

    @ApiModelProperty(value = "日记报告成绩")
    private Integer diaryScore;

    @ApiModelProperty(value = "代码检查名称")
    private String codeTitle;

    @ApiModelProperty(value = "代码检查位置")
    private String codeLocation;

    @ApiModelProperty(value = "代码检查创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "Asia/Shanghai")
    private LocalDate codeCreateTime;

    @ApiModelProperty(value = "代码检查报告成绩")
    private Integer codeScore;

    @ApiModelProperty(value = "总结报告名称")
    private String summaryTitle;

    @ApiModelProperty(value = "总结报告位置")
    private String summaryLocation;

    @ApiModelProperty(value = "总结报告创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "Asia/Shanghai")
    private LocalDate summaryCreateTime;

    @ApiModelProperty(value = "总结报告报告成绩")
    private Integer summaryScore;

    @ApiModelProperty(value = "总成绩")
    private Integer score;

    @ApiModelProperty(value = "评语")
    private String comment;


}
