 package cn.csvdemo.util;

import java.io.File;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author xiang
 * @date 2018/12/12
 */
public class FileUtil {
    public static void uploadFile(MultipartFile file, String filePath, String fileName) throws Exception {
    File targetFile = new File(filePath);
    if (!targetFile.exists()) {
        targetFile.mkdirs();
    }
    file.transferTo(new File(filePath + fileName));
    
}


}
