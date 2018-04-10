package com.jeiker.fastdfs.controller;

import com.jeiker.fastdfs.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UploadController {

    private static Logger logger = LoggerFactory.getLogger(UploadController.class);

    @Autowired
    private FileService fileService;

    @GetMapping("/")
    public String index() {
        return "upload";
    }

    @GetMapping("/uploadStatus")
    public String uploadStatus() {
        return "uploadStatus";
    }

    @PostMapping("/upload")
    public String singleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) {
        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "redirect:uploadStatus";
        }
        try {
            String path = fileService.saveFile(file);
            redirectAttributes.addFlashAttribute("message", "You successfully uploaded '"
                    + file.getOriginalFilename() + "'");
            redirectAttributes.addFlashAttribute("path", "file path url '" + path + "'");
        } catch (Exception e) {
            logger.error("upload file failed:{}, {}", e.getMessage(), e);
        }
        return "redirect:/uploadStatus";
    }


}
