package com.hanergy.reportForms.mapper;

import com.hanergy.reportForms.mapper.entity.ProcessRecode;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ProcessRecodeMapper {
    public List<Map<String,Object>> processRecodeList(ProcessRecode processRecode);
    public Integer countRecode(ProcessRecode processRecode);
}
