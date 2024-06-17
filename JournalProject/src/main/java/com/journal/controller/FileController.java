package com.journal.controller;

import com.journal.pojo.Article;
import com.journal.pojo.basicClass.User;
import com.journal.service.UserService;
import com.journal.utils.ApiResponse;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.*;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;


@RestController
@RequestMapping("/files")
public class FileController {
    @Autowired
    UserService userService;

    // 上传文件的存储路径
    private static String uploadDir = "D:/JournalData/";

    @RequestMapping("/upload")
    public ApiResponse<String> uploadFile(@RequestParam("file") MultipartFile file, Article article, HttpServletRequest request) {
        // 获取当前用户，设置文章的用户id和类别id
//        User user = (User) request.getSession().getAttribute("user");
//        article.setUserID(user.getUserID());
        article.setUserID(1001);
        int categoryId = userService.findCategoryIdByName(article.getCategoryName());
        article.setCategoryID(categoryId);

        // 生成不重复的文件名
        String fileName = StringUtils.cleanPath(UUID.randomUUID().toString() + "_" + file.getOriginalFilename());
        // 将文件名记录在文章属性中
        article.setFilepath(fileName);

        // 确保存储目录存在
        File dir = new File(uploadDir);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        // 将文件保存到服务器端
        File targetFile = new File(uploadDir + fileName);
        try {
            file.transferTo(targetFile);
            // 将文件路径存储到数据库中
            userService.saveArticle(article);
            return new ApiResponse<>(true, "File uploaded successfully!", uploadDir + fileName);
        } catch (IOException e) {
            e.printStackTrace();
            return new ApiResponse<>(false, "Failed to upload file.", null);
        }
    }

        @GetMapping("/download")
    public ResponseEntity<byte[]> downloadFile(@RequestParam("articleID")int articleID) throws IOException {
        //根据id查询数据库来查询文件名称
        // 构建文件路径
        Path fileAbsolutePath = Paths.get("D:\\JournalData\\83e49e12-8306-41ac-9772-2d8d87566f2c_《统计学习方法》课程项目报告书(1).doc");
        InputStream inputStream = new FileInputStream(fileAbsolutePath.toFile());

        // 读取文件内容
        byte[] fileBytes = inputStream.readAllBytes();

        // 设置下载的文件名
        String fileName = "customFileName.doc";

        // 设置响应头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", fileName);

        // 返回文件字节流
        return new ResponseEntity<>(fileBytes, headers, HttpStatus.OK);
    }

    @RequestMapping("/show")
    public ApiResponse<String> getFileContent() {
        // 文件路径
        Path filePath = Paths.get("D:\\JournalData\\83e49e12-8306-41ac-9772-2d8d87566f2c_《统计学习方法》课程项目报告书(1).doc");

        // 读取文件内容
        String fileContent;
        try (FileInputStream fis = new FileInputStream(filePath.toFile())) {
            HWPFDocument document = new HWPFDocument(fis);
            WordExtractor extractor = new WordExtractor(document);
            fileContent = extractor.getText();
        } catch (IOException e) {
            e.printStackTrace();
            // 如果读取文件失败，则返回错误响应
            return new ApiResponse<>(false, "Failed to read file", null);
        }
        // 构建成功响应
        return new ApiResponse<>(true, "File content retrieved successfully", fileContent);
    }
}

