import net.onemost.generator.core.Bucket;
import net.onemost.generator.core.GeneratorMain;
import net.onemost.generator.strategy.DateStrategy;
import net.onemost.generator.strategy.StrategyUtil;
import net.onemost.generator.utils.RandomUtils;
import org.junit.Test;

public class MainTest {

    @Test
    public void test() throws Exception {
        Bucket bucket = Bucket.create("t_member").setNum(1000)
                .addFiled("id", String.class)
                    .setStrategy(StrategyUtil.UUID_GENERATOR)
                .addFiled("username", String.class)
                    .allowRepeat(false).setStrategy(StrategyUtil.EMAIL_GENERATOR)
                .addFiled("password", String.class)
                    .setStrategy(StrategyUtil.PASSWORD_GENERATOR)
                .addFiled("wx_id", String.class)
                    .setStrategy(StrategyUtil.UUID_GENERATOR)
                .addFiled("wx_name", String.class)
                    .setStrategy(StrategyUtil.CHINESE_NAME_GENERATOR)
                .addFiled("head_img", String.class)
                    .setStrategy(() -> "http://img2.imgtn.bdimg.com/it/u=2175610530,2932833450&fm=26&gp=0.jpg")
                .addFiled("gender", Byte.class)
                    .setStrategy(() -> RandomUtils.aByte(0, 2))
                .addFiled("create_time", Long.class)
                    .setStrategy(DateStrategy.builder().build())
                .addFiled("shop_id", String.class)
                    .setOtherTable("t_shop", "id")
                    .singleColumn(obj -> obj)
                .addFiled("city_id", String.class)
                    .setOtherTable("t_area", "id")
                    .singleColumn(obj -> obj)
                .addFiled("city_name", String.class)
                    .setOtherTable("t_area", "name")
                    .singleColumn(obj -> obj);



        new GeneratorMain("root", "fish9670", "jdbc:mysql://47.100.165.72:6000/pos?charset=utf-8&useSSL=false", "com.mysql.cj.jdbc.Driver")
                .start(bucket);
    }

}
