package com.leewaiho.togogo.common.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


/**
 * @Author leewaiho
 * @Email 791783391@qq.com
 * @Date 2017/8/27
 */
public class FileUtil {
    
    public static File saveAsFile(byte[] bytes, String pathname) throws IOException {
        File target = new File(pathname);
        File parentFile = target.getParentFile();
        if (parentFile.exists() || parentFile.mkdirs()) {
            FileOutputStream fileOutputStream = new FileOutputStream(target, false);
            fileOutputStream.write(bytes);
            fileOutputStream.close();
        }
        return target;
    }
}
