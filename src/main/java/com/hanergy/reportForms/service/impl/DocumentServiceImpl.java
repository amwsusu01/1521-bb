package com.hanergy.reportForms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hanergy.reportForms.mapper.DocumentMapper;
import com.hanergy.reportForms.mapper.entity.Document;
import com.hanergy.reportForms.service.IDocumentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 文件表 服务实现类
 * </p>
 *
 * @author fangshuai
 * @since 2018-10-23
 */
@Service
public class DocumentServiceImpl extends ServiceImpl<DocumentMapper, Document> implements IDocumentService {

    @Override
    public List<Document> getFiles(Long serviceId) {
        QueryWrapper<Document> wrapper = new QueryWrapper<>();
        wrapper.eq("service_id",serviceId);
        return this.list(wrapper);
    }
}
