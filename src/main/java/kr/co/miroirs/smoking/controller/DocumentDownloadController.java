package kr.co.miroirs.smoking.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.miroirs.smoking.dto.FileInfo;
import kr.co.miroirs.smoking.service.DownloadService;

@Controller
public class DocumentDownloadController {

    @Autowired
    DownloadService downloadService;
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @GetMapping("/download")
    public void downloadDocument(@RequestParam(name="fileId", required=true) int fileId,
            HttpServletRequest request, HttpServletResponse response) {
        
        FileInfo fileInfo = downloadService.getFileInfo(fileId);
        
        String filePath = request.getServletContext().getRealPath(fileInfo.getFilePath());
        logger.debug("{}", filePath);
        File file = new File(filePath);
        int fileSize = (int) file.length();
        
        response.setHeader("Content-Disposition", 
                "attachment; filename=\"" + fileInfo.getFileName() + "\";");
        response.setHeader("Content-Transfer-Encoding", "binary");
        response.setHeader("Content-Type", fileInfo.getFileType());
        response.setHeader("Content-Length", Integer.toString(fileSize));
        response.setHeader("Cache-Control", "no-cache;");
        response.setHeader("Expires", "-1;");
        
        try (
            FileInputStream fis = new FileInputStream(filePath);
            OutputStream out = response.getOutputStream();
                ) {
            
            int readCount = 0;
            byte[] buffer = new byte[1024];
            
            while((readCount = fis.read(buffer)) != -1) {
                out.write(buffer, 0, readCount);
            }            
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
    }
}
