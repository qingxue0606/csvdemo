 package cn.csvdemo.controller;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.csvreader.CsvReader;

import cn.csvdemo.util.FileUtil;



/**
 * @author xiang
 * @date 2018/12/12
 */
 @Controller
public class UploadController {
     @RequestMapping(value = "/uploadPre", method = RequestMethod.GET)
     public String uploadPre() { // 通过model可以实现内容的传递
         return "upload_page";
     }
     @RequestMapping(value = "/upload", method = RequestMethod.POST)
     @ResponseBody
     public String uploadImg(@RequestParam("file") MultipartFile[] files,
             HttpServletRequest request) {
         for (MultipartFile file : files) {
             // 文件名
             String fileName = file.getOriginalFilename();
             try {
                 CsvReader csvReader = new CsvReader(file.getInputStream(),Charset.defaultCharset());

                 // 读表头
                 csvReader.readHeaders();

                 // 读内容
                 while (csvReader.readRecord()) {
                     // 读一整行
                     System.out.println(csvReader.getRawRecord());
                     // 读该行的某一列
                     System.out.println(csvReader.get("Link"));
                 }
             } catch (IOException e) {
                 e.printStackTrace();
             }

             if("".equals(fileName)){
                 continue;
             }
             // 文件后缀
             String suffx = fileName.substring(fileName.lastIndexOf('.'));
             String filePath = request.getSession().getServletContext()
                     .getRealPath("imgupload/");
             try {
                 FileUtil.uploadFile(file, filePath, new Date().getTime()
                         + suffx);
             } catch (Exception e) {
                 e.printStackTrace();
             }
         }
         // 返回json
         return "uploadimg success";
     }


}
