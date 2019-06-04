package com.hanergy.reportForms.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hanergy.reportForms.commons.enums.EnumBeSelectedStatus;
import com.hanergy.reportForms.dto.BeSelectedNeed;
import com.hanergy.reportForms.dto.third.MediCadidate;
import com.hanergy.reportForms.mapper.entity.BeSelected;

/**
 * <p>
 * 候选人 服务类
 * </p>
 *
 * @author duronghong
 * @since 2018-09-12
 */
public interface IBeSelectedService extends IService<BeSelected> {
    /**
     * 获取候选人的所有信息,为封装,直接读库
     *
     * @param id
     * @return
     */
    BeSelected getBeSelectedInfo(Long id, BeSelectedNeed need);

    /**
     * 分页查询
     *
     * @param beSelectedPage
     * @param queryWrapper
     * @return
     */
    JSONObject selctPage(Page<BeSelected> beSelectedPage, QueryWrapper<BeSelected> queryWrapper);

    /**
     * 获取所选模板项目中所能匹配到的能够负责调查这些项目的中介公司
     *
     * @return
     */
    JSONObject getTemplateMaps();

    /**
     * 流程中的信息修改
     *
     * @param beSelected
     * @return
     * @throws Exception
     */
    JSONObject updateBeselected(BeSelected beSelected) throws Exception;

    /**
     * 保存候选人填写的简历
     *
     * @param resumeDto
     * @return
     */
    JSONObject saveResumeForCandidate(BeSelected beselectedInfo) throws Exception;

    /**
     * 启动背调
     *
     * @param beSelected
     * @return
     */
    JSONObject startInvest(BeSelected beSelected) throws Exception;

    /**
     * 背调负责人审批,完成背调流程
     * 状态 : 3-->4
     *
     * @param beSelected
     * @return
     * @throws Exception
     */
    JSONObject complete(BeSelected beSelected) throws Exception;

    /**
     * @param beSelected
     * @return
     */
    JSONObject bussinesOperation(BeSelected beSelected) throws Exception;

    MediCadidate getMediCandidateById(Long beSelected);

    /**
     * 发送邮件给业务负责人
     *
     * @param beSelected
     * @return
     */
    JSONObject sendMailToBussinessLeader(BeSelected beSelected);

    /**
     * 根据候选人ID,返回候选人信息和候选人上传简历详情
     *
     * @param beSelectId
     * @return
     */
    BeSelected getBeSelectedResumeById(Long beSelectId);


    /**
     * 获取连接调查候选人列表
     *
     * @param UserId   填写则查询与该用户相关联的候选人
     * @param keyword
     * @param page
     * @param pageSize
     * @return
     */
    JSONObject getMachineInvestList(Long UserId, String keyword, Integer page, Integer pageSize);

    /**
     * 获取工作经历核实候选人列表
     *
     * @param UserId   填写则查询与该用户相关联的候选人
     * @param keyword
     * @param page
     * @param pageSize
     * @return
     */
    JSONObject getWorkExperenceVeriftList(Long UserId, String keyword, Integer page, Integer pageSize);

    /**
     * 修改候选人详情
     *
     * @param beSelected
     * @param checkStatus
     * @return
     */
    JSONObject updateCheckStatus(Long beSelected, EnumBeSelectedStatus checkStatus);

    /**
     * SF系统同步候选人简历信息
     *
     * @param url
     */
    void sfCandidateToBeSelected(String url) throws Exception;

    /**
     * 校验背调是否只有自主背调
     * @param beSelected
     * @return 1-只有自主背调  2- 只有中介背调 3-自主背调和中介背调同事存在
     */
    Integer verifyCheckIsOnlySelf(Long beSelected);


    public JSONObject postCheckStatusToSf(Long beSelectedId);

}
