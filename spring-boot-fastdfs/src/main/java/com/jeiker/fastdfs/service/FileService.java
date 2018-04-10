package com.jeiker.fastdfs.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileService {

    String saveFile(MultipartFile multipartFile) throws IOException;
}
