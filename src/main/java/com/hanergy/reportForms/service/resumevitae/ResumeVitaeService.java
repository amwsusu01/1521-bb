package com.hanergy.reportForms.service.resumevitae;

import com.hanergy.reportForms.mapper.entity.BeSelected;

/*
* 简历对比
* */

public interface ResumeVitaeService {

     /*
     *对比候选的的简历（候选人自填与人力填写进行对比,暂比身份信息和教育背景）
      */
     BeSelected getResumeVitaeResult(BeSelected beSelected);
}
