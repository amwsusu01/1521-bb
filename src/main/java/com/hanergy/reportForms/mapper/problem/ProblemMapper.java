package com.hanergy.reportForms.mapper.problem;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hanergy.reportForms.mapper.entity.Problem;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ProblemMapper  {

    public List<Map<String,Object>> problemlist(Problem problem);

    public Integer countProblem(Problem problem);
}
