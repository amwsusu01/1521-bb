package com.hanergy.reportForms.mapper;

import com.hanergy.reportForms.mapper.entity.JobApplication;
import com.hanergy.reportForms.mapper.entity.UrlData;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface PersonnelFlowMapper {

    public List<Map> findByParam(JobApplication jobApplication);
    public List<Map> find1521bakByCreateDate();
    public List<Map<String,String>> findQueryParam(JobApplication jobApplication);
    public Integer countJobApplication(JobApplication jobApplication);
    public Integer createPersonnelFlow(List<JobApplication> list);
    public Integer createPersonnelFlowBak(List<JobApplication> list);
    public void truncatePersonnelFlow();
    public List<UrlData> queryData();
    public Integer createUrlDate(List<UrlData>list);

    public String findLastModifiedDateTime();
}
