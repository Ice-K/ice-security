package com.ice.web.controller;

import com.ice.dto.FileInfo;
import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;

/**
 * Description：
 * Cteated by wangpeng
 * 2018/3/9 15:22
 */
@RestController
@RequestMapping("/file")
public class FileController {
    private String folder = "F:\\IdeaProjects\\ice-security\\ice-security-demo\\src\\main\\java\\com\\ice\\web\\controller";

    @PostMapping
    public FileInfo upload(MultipartFile file) throws IOException {
        System.out.println(file.getName());
        System.out.println(file.getSize());
        System.out.println(file.getOriginalFilename());//原始文件名


        File localFile = new File(folder, new Date().getTime() + ".txt");

        file.transferTo(localFile);
        return new FileInfo(localFile.getAbsolutePath());
    }


    /**
     * 下载
     * @param id
     * @param request
     * @param response
     */
    @GetMapping("/{id}")
    public void download(@PathVariable String id, HttpServletRequest request, HttpServletResponse response) {
        try (
                InputStream inputStream = new FileInputStream(new File(folder, id + ".txt"));
                OutputStream outputStream = response.getOutputStream()
        ){
                response.setContentType("application/x-download");
                response.addHeader("Content-Disposition","attachment;filename=test.txt");

            IOUtils.copy(inputStream,outputStream);
            outputStream.flush();
        } catch (Exception e) {

        }
    }
}
