package com.hanergy.reportForms.service.impl;

import com.hanergy.reportForms.commons.fastdfs.FastDFSClient;
import com.hanergy.reportForms.commons.fastdfs.FastDFSFile;
import com.hanergy.reportForms.commons.utils.LogUtils;
import com.hanergy.reportForms.service.IUploadService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

/**
 * @ClassName UploadServiceImpl
 * @Description TODO
 * @Author DURONGHONG
 * @DATE 2018/10/24 11:27
 * @Version 1.0
 **/
@Service
public class UploadServiceImpl implements IUploadService {


    @Value("${project.background-system.staticUrl}")
    private String staticServerUrl;

    @Override
    public String uploadFile(MultipartFile file) throws IOException {
        return saveFile(file);
    }


    /**
     * @param multipartFile
     * @return
     * @throws IOException
     */
    private String saveFile(MultipartFile multipartFile) throws IOException {
        String[] fileAbsolutePath = {};
        String fileName = multipartFile.getOriginalFilename();
        String ext = fileName.substring(fileName.lastIndexOf(".") + 1);
        byte[] file_buff = null;
        InputStream inputStream = multipartFile.getInputStream();
        int len1 = inputStream.available();
        file_buff = new byte[len1];
        inputStream.read(file_buff);
        inputStream.close();
        FastDFSFile file = new FastDFSFile(fileName, file_buff, ext);
        try {
            fileAbsolutePath = FastDFSClient.upload(file);  //upload to fastdfs
        } catch (Exception e) {
            LogUtils.getExceptionLogger().error("upload file Exception!", e);
        }
        if (fileAbsolutePath == null) {
            LogUtils.getExceptionLogger().error("upload file failed,please upload again!");
        }
//        String path = FastDFSClient.getTrackerUrl() + fileAbsolutePath[0] + "/" + fileAbsolutePath[1];
        String path = staticServerUrl + fileAbsolutePath[0] + "/" + fileAbsolutePath[1];
        return path;
    }
}
