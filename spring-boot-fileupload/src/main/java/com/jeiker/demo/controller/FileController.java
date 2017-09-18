package com.jeiker.demo.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Collections;
import java.util.Map;
import java.util.logging.Logger;

/**
 * @Author : xiao
 * @Date : 17/9/18 下午4:16
 */
@RestController
@RequestMapping("/file")
public class FileController {

    private Logger log = Logger.getLogger(getClass().getName());

    /**
     * 单文件上传
     */
    @PostMapping("/upload")
    public Map<String, String> fileUpload(@RequestParam("files") MultipartFile file) {
        if (file.isEmpty()) {
            return Collections.singletonMap("message", "file is empty.");
        }
        String fileName = file.getOriginalFilename();
        int size = (int) file.getSize();
        log.info("文件名为: " + fileName + "文件大小: " + size);

        String path = "/Users/xiao/Downloads/test";
        File dest = new File(path + "/" + fileName);
        if (!dest.getParentFile().exists()) { //判断文件父目录是否存在
            dest.getParentFile().mkdir();
        }
        try {
            file.transferTo(dest); //保存文件
            return Collections.singletonMap("message", "file upload success.");
        } catch (IllegalStateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return Collections.singletonMap("message", "file upload failure.");

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return Collections.singletonMap("message", "file upload failure.");
        }
    }

    /**
     * 多文件上传
     */
    @PostMapping("/uploads")
    public Map<String, String> multipleSave(@RequestParam("files") MultipartFile[] files) {
        String fileName = null;
        if (files != null && files.length > 0) {
            for (MultipartFile file : files) {
                try {
                    fileName = file.getOriginalFilename();
                    byte[] bytes = file.getBytes();
                    String path = "/Users/xiao/Downloads/test";
                    BufferedOutputStream buffStream =
                            new BufferedOutputStream(new FileOutputStream(new File(path + "/" + fileName)));
                    buffStream.write(bytes);
                    buffStream.close();
                } catch (Exception e) {
                    return Collections.singletonMap("message", "file upload failure.");
                }
            }
            return Collections.singletonMap("message", "file upload success.");
        } else {
            return Collections.singletonMap("message", "file is empty.");
        }
    }

    /**
     * 文件下载
     */
    @GetMapping("/download")
    public void testDownload(HttpServletResponse resp) throws IOException {
        File file = new File("/Users/xiao/Downloads/test/xx.png");
        String fileName = "xx";
        resp.setHeader("content-type", "application/octet-stream");
        resp.setContentType("application/octet-stream");
        resp.setHeader("Content-Disposition", "attachment;filename=" + fileName);
        byte[] buff = new byte[1024];
        BufferedInputStream bis = null;
        OutputStream os = null;
        try {
            os = resp.getOutputStream();
            bis = new BufferedInputStream(new FileInputStream(file));
            int i = bis.read(buff);
            while (i != -1) {
                os.write(buff, 0, buff.length);
                os.flush();
                i = bis.read(buff);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
