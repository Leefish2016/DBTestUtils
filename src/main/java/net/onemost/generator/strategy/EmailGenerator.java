package net.onemost.generator.strategy;

/**
 * 邮件生成策略
 * @author onemost
 */
public class EmailGenerator implements BaseStrategy<String> {

    public static final String[] EMAIL_SUFFIX={"@gmail.com", "@yahoo.com", "@msn.com", "@hotmail.com", "@aol.com", "@ask.com", "@live.com", "@qq.com", "@0355.net", "@163.com", "@163.net", "@263.net", "@3721.net", "@yeah.net", "@googlemail.com", "@126.com", "@sina.com", "@sohu.com", "@yahoo.com.cn"};

    public static final String[] LETTER = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "_", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};

    private int minLen;

    private int maxLen;

    protected  EmailGenerator(){
        this.minLen = 3;
        this.maxLen = 6;
    }

    protected EmailGenerator(int minLen, int maxLen){
        this.minLen = minLen;
        this.maxLen = maxLen;
    }

    @Override
    public String generator() {
        int length= minLen + RANDOM.nextInt(maxLen - minLen);
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < length; i++) {
            stringBuffer.append(LETTER[RANDOM.nextInt(LETTER.length)]);
        }
        stringBuffer.append(EMAIL_SUFFIX[RANDOM.nextInt(EMAIL_SUFFIX.length)]);
        return stringBuffer.toString();
    }

    public void setLen(int minLen, int maxLen){
        this.minLen = minLen;
        this.maxLen = maxLen;
    }
}
