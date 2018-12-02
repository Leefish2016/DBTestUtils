package net.onemost.generator.strategy;

import lombok.Builder;

/**
 * 日期生成策略
 * @author onemost
 */
@Builder
public class DateStrategy implements BaseStrategy<Long>{

    @Override
    public Long generator() {
        return System.currentTimeMillis();
    }
}
