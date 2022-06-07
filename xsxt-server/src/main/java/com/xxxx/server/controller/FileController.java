package com.xxxx.server.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xxxx.server.mapper.FileMapper;
import com.xxxx.server.mapper.SubjectMapper;
import com.xxxx.server.pojo.Admin;
import com.xxxx.server.pojo.File;
import com.xxxx.server.pojo.RespBean;
import com.xxxx.server.pojo.Subject;
import com.xxxx.server.service.IFileService;
import com.xxxx.server.service.ISubjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.jdbc.Null;
import org.apache.tomcat.util.bcel.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.plaf.metal.MetalIconFactory;
import java.io.*;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author fangyu
 * @since 2022-03-20
 */
@RestController

@RequestMapping("/file")
public class FileController {

    @Autowired
    private IFileService fileService;

    @Autowired
    private ISubjectService subjectService;

    @Autowired
    private FileMapper fileMapper;

    @Autowired
    private SubjectMapper subjectMapper;


    @ApiOperation(value = "获取所有文件")
    @GetMapping("/")
    public List<File> getAllFile() {
        return fileService.list();
    }


    @ApiOperation(value = "创建文件信息")
    @PostMapping("/")
    public RespBean addFile(@RequestBody File file) {
        if (fileService.save(file)) {
            return RespBean.success("上传成功！");
        }
        return RespBean.error("上传失败！");
    }

    @ApiOperation(value = "更新文件信息(仅成绩，文本)")
    @PutMapping("/")
    public RespBean updateFile(@RequestBody File file) {
        file.setScore(file.getProposalScore() + file.getDemandScore() + file.getDiaryScore() + file.getCodeScore() + file.getSummaryScore());
        if (fileService.updateById(file)) {
            return RespBean.success("更新成功!");
        }
        return RespBean.error("更新失败!");
    }

    @ApiOperation(value = "删除文件")
    @DeleteMapping("/{id}")
    public RespBean deleteAdmin(@PathVariable Integer id) {
        if (fileService.removeById(id)) {
            return RespBean.success("删除成功!");
        }
        return RespBean.error("删除失败!");
    }

    //未完成
    @ApiOperation(value = "批量删除管理员")
    @DeleteMapping("/")
    public RespBean deleteAdminById(String[] ids) {
        if (fileService.removeByIds(Arrays.asList(ids))) {
            return RespBean.success("删除成功!");
        }
        return RespBean.error("删除失败!");
    }


    @ApiOperation(value = "导出课题内容文件")
    @GetMapping(value = "/export/content/", produces = "application/octet-stream")
    public void exporContenttFile(Integer fileId, HttpServletResponse response) throws IOException {
        FileInputStream in = null;
        ServletOutputStream out = null;
        File file = fileService.getById(fileId);
        try {
            in = new FileInputStream("F:/file/" + file.getContentTitle());
            response.setContentType("application/octet-stream;charset=utf-8");
            response.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode(file.getContentTitle(), "UTF-8"));
            out = response.getOutputStream();
            int len = 0;
            byte[] buffer = new byte[2 * 1024];
            while ((len = in.read(buffer)) > 0) {
                out.write(buffer, 0, len);
            }
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
            if (in != null) {
                in.close();
            }
        }
    }

    @ApiOperation(value = "导出开题报告文件")
    @GetMapping(value = "/export/proposal/", produces = "application/octet-stream")
    public void exportProposalFile(Integer fileId, HttpServletResponse response) throws IOException {
        FileInputStream in = null;
        ServletOutputStream out = null;
        File file = fileService.getById(fileId);
        try {
            in = new FileInputStream("F:/file/" + file.getProposalTitle());
            response.setContentType("application/octet-stream;charset=utf-8");
            response.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode(file.getProposalTitle(), "UTF-8"));
            out = response.getOutputStream();
            int len = 0;
            byte[] buffer = new byte[2 * 1024];
            while ((len = in.read(buffer)) > 0) {
                out.write(buffer, 0, len);
            }
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
            if (in != null) {
                in.close();
            }
        }
    }

    @ApiOperation(value = "导出需求分析文件")
    @GetMapping(value = "/export/demand/", produces = "application/octet-stream")
    public void exportDemandFile(Integer fileId, HttpServletResponse response) throws IOException {
        FileInputStream in = null;
        ServletOutputStream out = null;
        File file = fileService.getById(fileId);
        try {
            in = new FileInputStream("F:/file/" + file.getDemandTitle());
            response.setContentType("application/octet-stream;charset=utf-8");
            response.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode(file.getDemandTitle(), "UTF-8"));
            out = response.getOutputStream();
            int len = 0;
            byte[] buffer = new byte[2 * 1024];
            while ((len = in.read(buffer)) > 0) {
                out.write(buffer, 0, len);
            }
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
            if (in != null) {
                in.close();
            }
        }
    }

    @ApiOperation(value = "导出实践日记文件")
    @GetMapping(value = "/export/diary/", produces = "application/octet-stream")
    public void exportDiaryFile(Integer fileId, HttpServletResponse response) throws IOException {
        FileInputStream in = null;
        ServletOutputStream out = null;
        File file = fileService.getById(fileId);
        try {
            in = new FileInputStream("F:/file/" + file.getDiaryTitle());
            response.setContentType("application/octet-stream;charset=utf-8");
            response.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode(file.getDiaryTitle(), "UTF-8"));
            out = response.getOutputStream();
            int len = 0;
            byte[] buffer = new byte[2 * 1024];
            while ((len = in.read(buffer)) > 0) {
                out.write(buffer, 0, len);
            }
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
            if (in != null) {
                in.close();
            }
        }
    }

    @ApiOperation(value = "导出代码检查文件")
    @GetMapping(value = "/export/code/", produces = "application/octet-stream")
    public void exportCodeFile(Integer fileId, HttpServletResponse response) throws IOException {
        FileInputStream in = null;
        ServletOutputStream out = null;
        File file = fileService.getById(fileId);
        try {
            in = new FileInputStream("F:/file/" + file.getCodeTitle());
            response.setContentType("application/octet-stream;charset=utf-8");
            response.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode(file.getCodeTitle(), "UTF-8"));
            out = response.getOutputStream();
            int len = 0;
            byte[] buffer = new byte[2 * 1024];
            while ((len = in.read(buffer)) > 0) {
                out.write(buffer, 0, len);
            }
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
            if (in != null) {
                in.close();
            }
        }
    }

    @ApiOperation(value = "导出总结报告文件")
    @GetMapping(value = "/export/summary/", produces = "application/octet-stream")
    public void exportSummaryFile(Integer fileId, HttpServletResponse response) throws IOException {
        FileInputStream in = null;
        ServletOutputStream out = null;
        File file = fileService.getById(fileId);
        try {
            in = new FileInputStream("F:/file/" + file.getSummaryTitle());
            response.setContentType("application/octet-stream;charset=utf-8");
            response.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode(file.getSummaryTitle(), "UTF-8"));
            out = response.getOutputStream();
            int len = 0;
            byte[] buffer = new byte[2 * 1024];
            while ((len = in.read(buffer)) > 0) {
                out.write(buffer, 0, len);
            }
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
            if (in != null) {
                in.close();
            }
        }
    }


    @ApiOperation(value = "更新开题报告文件")
    @PostMapping("/upload/proposalFile")
    public RespBean updateProposalFile(@RequestParam("file") MultipartFile file, @RequestParam("subjectId") Integer subjectId) {

        File file1 = fileMapper.selectById(subjectId);
        String path = "F:/file/";
        String title = "";
        // 判断文件是否有内容
        if (file.isEmpty()) {
            return RespBean.error("上传失败!");
        }
        String fileName = file.getOriginalFilename();
        if (fileName.indexOf("\\") > 0) {
            int index = fileName.lastIndexOf("\\");
            fileName = fileName.substring(index + 1);
        }
        if (fileName.indexOf(".") >= 0) {
            String[] fileNameSplitArray = fileName.split("\\.");
            // 修改原文件名，防止附件重名覆盖原文件
            fileName = fileNameSplitArray[0] + (int) (Math.random() * 100000) + "." + fileNameSplitArray[1];
        }
        title = fileName;
        java.io.File dest = new java.io.File(path + fileName);
        // 如果pathAll路径不存在，则创建相关该路径涉及的文件夹;
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (file1.getProposalTitle() == "" || file1.getProposalTitle() == null) {
            //原先不存在
            file1.setProposalTitle(title);
            file1.setProposalLocation(path + fileName);
            file1.setProposalCreateTime(LocalDate.now());
            fileService.updateById(file1);
            return RespBean.success("上传成功!");
        } else {
            file1.setProposalTitle(title);
            file1.setProposalLocation(path + fileName);
            file1.setProposalCreateTime(LocalDate.now());
            fileService.updateById(file1);
            return RespBean.success("更新成功!");
        }

    }


    @ApiOperation(value = "删除开题报告文件")
    @DeleteMapping("/delete/proposalFile/{subjectId}")
    public RespBean updateProposalFile(@PathVariable("subjectId") Integer subjectId) {

        File file1 = fileMapper.selectById(subjectId);
        if (file1.getProposalTitle() == "" || file1.getProposalTitle() == null) {
            return RespBean.error("删除失败!");
        } else {
            file1.setProposalTitle("");
            file1.setProposalLocation("");
            //无法更新createTime
            file1.setProposalCreateTime(null);
            fileService.updateById(file1);
            return RespBean.success("删除成功!");
        }

    }

    @ApiOperation(value = "更新需求分析文件")
    @PostMapping("/upload/demandFile")
    public RespBean updateDemandFile(@RequestParam("file") MultipartFile file, @RequestParam("subjectId") Integer subjectId) {

        File file1 = fileMapper.selectById(subjectId);
        String path = "F:/file/";
        String title = "";
        // 判断文件是否有内容
        if (file.isEmpty()) {
            return RespBean.error("上传失败!");
        }
        String fileName = file.getOriginalFilename();
        if (fileName.indexOf("\\") > 0) {
            int index = fileName.lastIndexOf("\\");
            fileName = fileName.substring(index + 1);
        }
        if (fileName.indexOf(".") >= 0) {
            String[] fileNameSplitArray = fileName.split("\\.");
            // 修改原文件名，防止附件重名覆盖原文件
            fileName = fileNameSplitArray[0] + (int) (Math.random() * 100000) + "." + fileNameSplitArray[1];
        }
        title = fileName;
        java.io.File dest = new java.io.File(path + fileName);
        // 如果pathAll路径不存在，则创建相关该路径涉及的文件夹;
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (file1.getDemandTitle() == "" || file1.getDemandTitle() == null) {
            //原先不存在
            file1.setDemandTitle(title);
            file1.setDemandLocation(path + fileName);
            file1.setDemandCreateTime(LocalDate.now());
            fileService.updateById(file1);
            return RespBean.success("上传成功!");
        } else {
            file1.setDemandTitle(title);
            file1.setDemandLocation(path + fileName);
            file1.setDemandCreateTime(LocalDate.now());
            fileService.updateById(file1);
            return RespBean.success("更新成功!");
        }

    }


    @ApiOperation(value = "删除需求分析文件")
    @DeleteMapping("/delete/demandFile/{subjectId}")
    public RespBean updateDemandFile(@PathVariable("subjectId") Integer subjectId) {

        File file1 = fileMapper.selectById(subjectId);
        if (file1.getDemandTitle() == "" || file1.getDemandTitle() == null) {
            return RespBean.error("删除失败!");
        } else {
            file1.setDemandTitle("");
            file1.setDemandLocation("");
            //无法更新createTime
            file1.setDemandCreateTime(null);
            fileService.updateById(file1);
            return RespBean.success("删除成功!");
        }

    }

    @ApiOperation(value = "更新代码检查文件")
    @PostMapping("/upload/codeFile")
    public RespBean updateCodeFile(@RequestParam("file") MultipartFile file, @RequestParam("subjectId") Integer subjectId) {

        File file1 = fileMapper.selectById(subjectId);
        String path = "F:/file/";
        String title = "";
        // 判断文件是否有内容
        if (file.isEmpty()) {
            return RespBean.error("上传失败!");
        }
        String fileName = file.getOriginalFilename();
        if (fileName.indexOf("\\") > 0) {
            int index = fileName.lastIndexOf("\\");
            fileName = fileName.substring(index + 1);
        }
        if (fileName.indexOf(".") >= 0) {
            String[] fileNameSplitArray = fileName.split("\\.");
            // 修改原文件名，防止附件重名覆盖原文件
            fileName = fileNameSplitArray[0] + (int) (Math.random() * 100000) + "." + fileNameSplitArray[1];
        }
        title = fileName;
        java.io.File dest = new java.io.File(path + fileName);
        // 如果pathAll路径不存在，则创建相关该路径涉及的文件夹;
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (file1.getCodeTitle() == "" || file1.getCodeTitle() == null) {
            //原先不存在
            file1.setCodeTitle(title);
            file1.setCodeLocation(path + fileName);
            file1.setCodeCreateTime(LocalDate.now());
            fileService.updateById(file1);
            return RespBean.success("上传成功!");
        } else {
            file1.setCodeTitle(title);
            file1.setCodeLocation(path + fileName);
            file1.setCodeCreateTime(LocalDate.now());
            fileService.updateById(file1);
            return RespBean.success("更新成功!");
        }

    }


    @ApiOperation(value = "删除代码检查文件")
    @DeleteMapping("/delete/codeFile/{subjectId}")
    public RespBean updateCodeFile(@PathVariable("subjectId") Integer subjectId) {

        File file1 = fileMapper.selectById(subjectId);
        if (file1.getCodeTitle() == "" || file1.getCodeTitle() == null) {
            return RespBean.error("删除失败!");
        } else {
            file1.setCodeTitle("");
            file1.setCodeLocation("");
            //无法更新createTime
            file1.setCodeCreateTime(null);
            fileService.updateById(file1);
            return RespBean.success("删除成功!");
        }

    }

    @ApiOperation(value = "更新总结报告文件")
    @PostMapping("/upload/summaryFile")
    public RespBean updateSummaryFile(@RequestParam("file") MultipartFile file, @RequestParam("subjectId") Integer subjectId) {

        File file1 = fileMapper.selectById(subjectId);
        String path = "F:/file/";
        String title = "";
        // 判断文件是否有内容
        if (file.isEmpty()) {
            return RespBean.error("上传失败!");
        }
        String fileName = file.getOriginalFilename();
        if (fileName.indexOf("\\") > 0) {
            int index = fileName.lastIndexOf("\\");
            fileName = fileName.substring(index + 1);
        }
        if (fileName.indexOf(".") >= 0) {
            String[] fileNameSplitArray = fileName.split("\\.");
            // 修改原文件名，防止附件重名覆盖原文件
            fileName = fileNameSplitArray[0] + (int) (Math.random() * 100000) + "." + fileNameSplitArray[1];
        }
        title = fileName;
        java.io.File dest = new java.io.File(path + fileName);
        // 如果pathAll路径不存在，则创建相关该路径涉及的文件夹;
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (file1.getSummaryTitle() == "" || file1.getSummaryTitle() == null) {
            //原先不存在
            file1.setSummaryTitle(title);
            file1.setSummaryLocation(path + fileName);
            file1.setSummaryCreateTime(LocalDate.now());
            fileService.updateById(file1);
            return RespBean.success("上传成功!");
        } else {
            file1.setSummaryTitle(title);
            file1.setSummaryLocation(path + fileName);
            file1.setSummaryCreateTime(LocalDate.now());
            fileService.updateById(file1);
            return RespBean.success("更新成功!");
        }

    }


    @ApiOperation(value = "删除总结报告文件")
    @DeleteMapping("/delete/summaryFile/{subjectId}")
    public RespBean updateSummaryFile(@PathVariable("subjectId") Integer subjectId) {

        File file1 = fileMapper.selectById(subjectId);
        if (file1.getSummaryTitle() == "" || file1.getSummaryTitle() == null) {
            return RespBean.error("删除失败!");
        } else {
            file1.setSummaryTitle("");
            file1.setSummaryLocation("");
            //无法更新createTime
            file1.setSummaryCreateTime(null);
            fileService.updateById(file1);
            return RespBean.success("删除成功!");
        }

    }

    @ApiOperation(value = "更新课题内容文件")
    @PostMapping("/upload/contentFile")
    public RespBean updateContentFile(@RequestParam("file") MultipartFile file, @RequestParam("subjectId") Integer subjectId) {

        File file1 = fileMapper.selectById(subjectId);
        String path = "F:/file/";
        String title = "";
        // 判断文件是否有内容
        if (file.isEmpty()) {
            return RespBean.error("上传失败!");
        }
        String fileName = file.getOriginalFilename();
        if (fileName.indexOf("\\") > 0) {
            int index = fileName.lastIndexOf("\\");
            fileName = fileName.substring(index + 1);
        }
        if (fileName.indexOf(".") >= 0) {
            String[] fileNameSplitArray = fileName.split("\\.");
            // 修改原文件名，防止附件重名覆盖原文件
            fileName = fileNameSplitArray[0] + (int) (Math.random() * 100000) + "." + fileNameSplitArray[1];
        }else{
            return RespBean.error("上传失败!");
        }
        title = fileName;
        java.io.File dest = new java.io.File(path + fileName);
        // 如果pathAll路径不存在，则创建相关该路径涉及的文件夹;
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (file1.getContentTitle() == "" || file1.getContentTitle() == null) {
            //原先不存在
            file1.setContentTitle(title);
            file1.setContentLocation(path + fileName);
            file1.setContentCreateTime(LocalDate.now());
            fileService.updateById(file1);
            return RespBean.success("上传成功!");
        } else {
            file1.setContentTitle(title);
            file1.setContentLocation(path + fileName);
            file1.setContentCreateTime(LocalDate.now());
            fileService.updateById(file1);
            return RespBean.success("更新成功!");
        }

    }


    @ApiOperation(value = "删除课题内容文件")
    @DeleteMapping("/delete/contentFile/{subjectId}")
    public RespBean updateContentFile(@PathVariable("subjectId") Integer subjectId) {

        File file1 = fileMapper.selectById(subjectId);
        if (file1.getContentTitle() == "" || file1.getContentTitle() == null) {
            return RespBean.error("删除失败!");
        } else {
            file1.setContentTitle("");
            file1.setContentLocation("");
            //无法更新createTime
            file1.setContentCreateTime(null);
            fileService.updateById(file1);
            return RespBean.success("删除成功!");
        }

    }

    @ApiOperation(value = "更新实践日记文件")
    @PostMapping("/upload/diaryFile")
    public RespBean updateDiaryFile(@RequestParam("file") MultipartFile file, @RequestParam("subjectId") Integer subjectId) {

        File file1 = fileMapper.selectById(subjectId);
        String path = "F:/file/";
        String title = "";
        // 判断文件是否有内容
        if (file.isEmpty()) {
            return RespBean.error("上传失败!");
        }
        String fileName = file.getOriginalFilename();
        if (fileName.indexOf("\\") > 0) {
            int index = fileName.lastIndexOf("\\");
            fileName = fileName.substring(index + 1);
        }
        if (fileName.indexOf(".") >= 0) {
            String[] fileNameSplitArray = fileName.split("\\.");
            // 修改原文件名，防止附件重名覆盖原文件
            fileName = fileNameSplitArray[0] + (int) (Math.random() * 100000) + "." + fileNameSplitArray[1];
        }
        title = fileName;
        java.io.File dest = new java.io.File(path + fileName);
        // 如果pathAll路径不存在，则创建相关该路径涉及的文件夹;
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (file1.getDiaryTitle() == "" || file1.getDiaryTitle() == null) {
            //原先不存在
            file1.setDiaryTitle(title);
            file1.setDiaryLocation(path + fileName);
            file1.setDiaryCreateTime(LocalDate.now());
            fileService.updateById(file1);
            return RespBean.success("上传成功!");
        } else {
            file1.setDiaryTitle(title);
            file1.setDiaryLocation(path + fileName);
            file1.setDiaryCreateTime(LocalDate.now());
            fileService.updateById(file1);
            return RespBean.success("更新成功!");
        }

    }


    @ApiOperation(value = "删除实践日记文件")
    @DeleteMapping("/delete/diaryFile/{subjectId}")
    public RespBean updateDiaryFile(@PathVariable("subjectId") Integer subjectId) {

        File file1 = fileMapper.selectById(subjectId);
        if (file1.getDiaryTitle() == "" || file1.getDiaryTitle() == null) {
            return RespBean.error("删除失败!");
        } else {
            file1.setDiaryTitle("");
            file1.setDiaryLocation("");
            //无法更新createTime
            file1.setDiaryCreateTime(null);
            fileService.updateById(file1);
            return RespBean.success("删除成功!");
        }

    }

}
