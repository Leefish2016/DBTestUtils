package net.onemost.generator.strategy;

import com.alibaba.fastjson.JSON;
import net.onemost.generator.utils.FileUtils;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * 中文姓名生成策略
 * @author onemost
 */
public class ChineseNameGenerator implements BaseStrategy<String> {

    private static final List<String> NAMES ;

    static{
        NAMES = JSON.parseArray(FileUtils.getFileContent("chinese-name.json"), String.class);
    }

    protected ChineseNameGenerator(){}

    @Override
    public String generator() {
        int len = RANDOM.nextInt(2) + 1;
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(NAMES.get(RANDOM.nextInt(NAMES.size())));
        for(int i = 0;i < len;i++){
            stringBuffer.append(ChineseNameGenerator.generatorJianTi());
        }
        return stringBuffer.toString();
    }

    /**
     * 随机生成简体字
     * @return
     */
    public static String generatorJianTi(){
        int areaCode = (176 + Math.abs(RANDOM.nextInt(39)));
        int bitCode = (161 + Math.abs(RANDOM.nextInt(93)));
        byte[] bytes = new byte[2];
        bytes[0] = (new Integer(areaCode).byteValue());
        bytes[1] = (new Integer(bitCode).byteValue());
        String result = null;
        try
        {
            result = new String(bytes, "GBK");
        }
        catch (UnsupportedEncodingException ex)
        {
            result = "无";
            ex.printStackTrace();
        }finally {
            return result;
        }
    }
}
