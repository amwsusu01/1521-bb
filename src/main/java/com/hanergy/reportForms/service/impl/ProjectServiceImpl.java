package com.hanergy.reportForms.service.impl;

import com.hanergy.reportForms.mapper.ProjectMapper;
import com.hanergy.reportForms.mapper.entity.Project;
import com.hanergy.reportForms.service.IProjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.springframework.stereotype.Service;

/**
 * <p>
 * 实际工作项目 服务实现类
 * </p>
 *
 * @author fangshuai
 * @since 2018-10-12
 */
@Service
public class ProjectServiceImpl extends ServiceImpl<ProjectMapper, Project> implements IProjectService {

}
