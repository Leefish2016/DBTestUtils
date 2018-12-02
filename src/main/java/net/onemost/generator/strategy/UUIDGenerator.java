package net.onemost.generator.strategy;

import java.util.UUID;

/**
 * UUID生成ID
 * @author onemost
 */
public class UUIDGenerator implements BaseStrategy<String>{

    protected  UUIDGenerator(){}

    @Override
    public String generator() {
        return UUID.randomUUID().toString();
    }

}
