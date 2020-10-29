package com.cn.weixuan.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cn.weixuan.base.module.Response;
import com.cn.weixuan.pojo.Project;
import com.cn.weixuan.service.IProjectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @author YHY
 */
@Slf4j
@RestController
@RequestMapping("/project")
public class ProjectController {
    /**
     * 那确认一下
     * 查询 分页 ，需求和开发都可以查看
     * 增加、删除、修改 需求者，
     */

    @Autowired
    private IProjectService projectService;

    @Autowired
    private Response response;

    /**
     * 分页查询
     *
     * @param current 当前页
     * @param size    每页查询条数
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/listProject", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response sendRegistsms(int current, HttpServletRequest request) {
        log.info("查询项目列表");
        IPage<Project> page = new Page<>(current, 5);
        page = this.projectService.page(page, new LambdaQueryWrapper<Project>().orderByDesc(Project::getCreateTime));
        return response.success("查询项目列表成功！", page);
    }

    @ResponseBody
    @RequestMapping(value = "/addProject", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response addProject(Project project, HttpServletRequest request) {
        log.info("添加项目,{}", project.toString());
        project.setCreateTime(new Date());
        boolean b = this.projectService.save(project);
        log.info("添加项目,{}", b);
        return response.success("添加项目成功！");
    }

    @ResponseBody
    @RequestMapping(value = "/updateProject", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response updateProject(Project project, HttpServletRequest request) {
        log.info("修改项目");
        boolean b = this.projectService.update(project, new LambdaQueryWrapper<Project>().eq(Project::getId, project.getId()));
        log.info("修改项目,{}", b);
        return response.success( "修改项目成功！");
    }

    @ResponseBody
    @RequestMapping(value = "/detailsProject", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response detailsProject(String id, HttpServletRequest request) {
        log.info("查询项目");
        Project project = this.projectService.getById(id);
        return response.success("查询项目成功！", project);
    }

    @ResponseBody
    @RequestMapping(value = "/deleteProject", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response deleteProject(String id, HttpServletRequest request) {
        log.info("删除项目");
        boolean b = this.projectService.removeById(id);
        log.info("删除项目,{}", b);
        return response.success( "删除项目成功！");
    }
}
