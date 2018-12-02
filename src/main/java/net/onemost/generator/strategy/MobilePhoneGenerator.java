package net.onemost.generator.strategy;

/**
 * 手机号码生成
 * @author onemost
 */
public class MobilePhoneGenerator implements BaseStrategy<String> {

    protected MobilePhoneGenerator(){}

    private static final String[] TEL_PREFIX= {"134", "135", "136", "137", "138", "139", "150" , "151", "152", "157", "158", "159", "130", "131", "132", "155", "156", "133", "153", "170"};

    @Override
    public String generator() {
        String first=TEL_PREFIX[RANDOM.nextInt(TEL_PREFIX.length)];
        String second=String.valueOf(RANDOM.nextInt(888)+10000).substring(1);
        String third=String.valueOf(RANDOM.nextInt(9100)+10000).substring(1);
        return first+second+third;
    }
}
