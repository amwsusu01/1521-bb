package com.hanergy.reportForms.service;

import com.alibaba.fastjson.JSONObject;
import com.hanergy.reportForms.dto.sf.SFCadidate;

import java.util.List;

/**
 * SF系统传递同步数据入库
 */
public interface ISFSychroService {


    JSONObject saveResumes(List<SFCadidate> cadidates);

}
