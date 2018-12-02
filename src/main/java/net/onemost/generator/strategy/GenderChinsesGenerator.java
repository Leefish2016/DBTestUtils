package net.onemost.generator.strategy;

/**
 * 随机生成简体汉字的性别
 * @author onemost
 */
public class GenderChinsesGenerator implements BaseStrategy<String> {

    protected GenderChinsesGenerator(){}

    public static final String[] GENDER = {"男", "女"};

    @Override
    public String generator() {
        return GENDER[RANDOM.nextInt(GENDER.length)];
    }
}
