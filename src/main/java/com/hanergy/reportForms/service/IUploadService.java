package com.hanergy.reportForms.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface IUploadService {

    String uploadFile(MultipartFile file) throws IOException;
}
