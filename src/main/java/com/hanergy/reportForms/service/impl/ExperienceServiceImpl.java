package com.hanergy.reportForms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hanergy.reportForms.mapper.ExperienceMapper;
import com.hanergy.reportForms.mapper.entity.Experience;
import com.hanergy.reportForms.mapper.entity.Project;
import com.hanergy.reportForms.service.IExperienceService;
import com.hanergy.reportForms.service.IProjectService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 工作经历 服务实现类
 * </p>
 *
 * @author duronghong
 * @since 2018-09-12
 */
@Service
public class ExperienceServiceImpl extends ServiceImpl<ExperienceMapper, Experience> implements IExperienceService {


    @Autowired
    private IProjectService projectService;


    @Override
    public Experience getExperienceWithCheckInfo(Long experienceId) {
        return getById(experienceId);
    }

    @Override
    public List<Experience> getExperienceByBeSelectId(Long beSelectedId) {

        QueryWrapper<Experience> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("beSelected",beSelectedId);
        List<Experience> experiences = this.list(queryWrapper);
        if(CollectionUtils.isNotEmpty(experiences)){
            experiences = experiences.stream().peek(experience -> {
                QueryWrapper<Project> wrapper = new QueryWrapper<>();
                wrapper.eq("experience", experience.getId());
                List<Project> projects = projectService.list(wrapper);
                experience.setProjects(projects);
            }).collect(Collectors.toList());
        }
        return experiences;
    }
}
