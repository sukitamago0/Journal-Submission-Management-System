package com.journal.controller;

import com.journal.pojo.Article;
import com.journal.service.UserService;
import com.journal.utils.ApiResponse;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
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

    //文件上传api
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

    //文件下载api(download?articleID=)
    @GetMapping("/download")
    public ResponseEntity<byte[]> downloadFile(@RequestParam("articleID") int articleID) throws IOException {
        // 根据文章ID从数据库中获取文件路径
        Article article = userService.findArticleById(articleID);
        String filePath = article.getFilepath();
                // 构建文件路径
                Path fileAbsolutePath = Paths.get(uploadDir+filePath);
        InputStream inputStream = new FileInputStream(fileAbsolutePath.toFile());

        try {
            // 读取文件内容
            byte[] fileBytes = inputStream.readAllBytes();

            // 设置下载的文件名
            String fileName = article.getTitle()+".doc";

            // 设置响应头
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDispositionFormData("attachment", fileName);

            // 返回文件字节流
            return new ResponseEntity<>(fileBytes, headers, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            // 处理文件读取失败的情况
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        } finally {
            // 关闭输入流释放资源
            if (inputStream != null) {
                inputStream.close();
            }
        }
    }


    //内容传输api(download?articleID=)w
    @RequestMapping("/show")
    public ApiResponse<String> getFileContent(@RequestParam("articleID") int articleID) {
        // 根据文章ID从数据库中获取文件路径
        Article article = userService.findArticleById(articleID);
        String filePath1 = article.getFilepath();
        // 构建文件路径
        Path filePath = Paths.get(uploadDir+filePath1);
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

