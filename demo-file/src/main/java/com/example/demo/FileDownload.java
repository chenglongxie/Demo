package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
@Controller
@RequestMapping(value = "/file")
public class FileDownload {
    @RequestMapping(value = "/download")
    public void download(@RequestParam String uuid, @RequestParam String fileName, HttpServletRequest request,
                         HttpServletResponse response) {
        StringBuffer dirSb = new StringBuffer();
        dirSb.append(request.getSession().getServletContext().getRealPath(""));
        dirSb.append(File.separator);
        dirSb.append("upload");
        dirSb.append(File.separator);
        dirSb.append(uuid);
        dirSb.append(File.separator);
        dirSb.append(fileName);
        File file = new File(dirSb.toString());
        if (file.exists()) {
            try {
                response.setContentType("application/octet-stream");
                response.setHeader("Content-disposition",
                        "attachment; filename=" + new String(file.getName().getBytes("gbk"), "ISO8859-1"));
                FileInputStream fis = new FileInputStream(file);
                BufferedInputStream bis = new BufferedInputStream(fis);
                BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
                int read;
                byte b[] = new byte[1024];
                read = bis.read(b);
                while (read != -1) {
                    bos.write(b, 0, read);
                    read = bis.read(b);
                }
                fis.close();
                bis.close();
                bos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
