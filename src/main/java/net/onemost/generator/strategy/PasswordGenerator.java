package net.onemost.generator.strategy;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * 密码生成策略
 * @author onemost
 */
public class PasswordGenerator implements BaseStrategy<String> {


    private int maxLen = 13;

    private int minLen = 5;

    private boolean isMD5 = false;

    public static final String[] LETTER = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "_", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};

    @Override
    public String generator() {
        StringBuffer stringBuffer = new StringBuffer();
        int len = minLen + RANDOM.nextInt((maxLen - minLen));
        for(int i = 0;i < len;i++){
            stringBuffer.append(LETTER[RANDOM.nextInt(LETTER.length)]);
        }
        if(isMD5){
            return DigestUtils.md5Hex(stringBuffer.toString());
        }
        return stringBuffer.toString();
    }

}
