package net.onemost.generator.utils;

import lombok.extern.slf4j.Slf4j;
import net.onemost.generator.strategy.AddressGenerator;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * 文件操作工具类
 * @author onemost
 */
@Slf4j
public class FileUtils {

    private FileUtils(){}

    /**
     * 获取文本内容
     * @param filePath 文件相对于根目录的路径
     * @return
     */
    public static String getFileContent(String filePath){

        String path = AddressGenerator.class.getResource("/" + filePath).getPath();
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(path))){
            return bufferedReader.readLine();
        }catch (FileNotFoundException e){
            log.error(String.format("加载策略文件失败 找不到文件：%s", path));
        }catch (IOException  e){
            log.error(String.format("读取策略文件失败 信息：%s", e.getMessage()));
        }
        return null;
    }
}
