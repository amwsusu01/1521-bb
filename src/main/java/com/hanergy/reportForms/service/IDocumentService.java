package com.hanergy.reportForms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hanergy.reportForms.mapper.entity.Document;

import java.util.List;

/**
 * <p>
 * 文件表 服务类
 * </p>
 *
 * @author fangshuai
 * @since 2018-10-23
 */
public interface IDocumentService extends IService<Document> {

    /**
     * 通过业务id获取文件列表
     *
     * @param serviceId 多中表的id
     * @return
     */
    List<Document> getFiles(Long serviceId);
}
