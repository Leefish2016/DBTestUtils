package net.onemost.generator.strategy;

/**
 * 获取系统内置策略类型
 * @author onemost
 */
public class StrategyUtil {

    private StrategyUtil(){}

    public static final ChineseNameGenerator CHINESE_NAME_GENERATOR = new ChineseNameGenerator();

    public static final GenderChinsesGenerator GENDER_CHINSES_GENERATOR = new GenderChinsesGenerator();

    public static final IdCardGenerator ID_CARD_GENERATOR = new IdCardGenerator();

    public static final MobilePhoneGenerator MOBILE_PHONE_GENERATOR = new MobilePhoneGenerator();

    public static final EmailGenerator EMAIL_GENERATOR = new EmailGenerator();

    public static final UUIDGenerator UUID_GENERATOR = new UUIDGenerator();

    public static final AddressGenerator ADDRESS_GENERATOR = new AddressGenerator();

    public static final PasswordGenerator PASSWORD_GENERATOR = new PasswordGenerator();

}
