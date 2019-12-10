package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.*;

@Controller
@RequestMapping(value = "/file")
public class FileUpload {

    @RequestMapping(value = "/upload")
    @ResponseBody
    public Map uploadFile(@RequestParam("file") MultipartFile[] files, HttpServletRequest request) throws Exception {
        Map map = new HashMap();
        String uuid = UUID.randomUUID().toString().replace("-", "");
        StringBuffer dirSb = new StringBuffer();
        dirSb.append(request.getSession().getServletContext().getRealPath(""));
        dirSb.append(File.separator);
        dirSb.append("upload");
        dirSb.append(File.separator);
        dirSb.append(uuid);
        dirSb.append(File.separator);
        File outDir = new File(dirSb.toString());
        if (!outDir.exists()) {
            outDir.mkdirs();
        }
        List list = new ArrayList();
        for (MultipartFile file : files) {
            String fileName = file.getOriginalFilename();
            file.transferTo(new File(dirSb.toString() + fileName));
            list.add(fileName);
        }
        map.put("uuid", uuid);
        map.put("file", list);
        return map;
    }
}
