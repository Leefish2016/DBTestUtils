package net.onemost.generator.strategy;

import java.util.Random;

/**
 * 策略接口
 * @param <T>
 */
public interface BaseStrategy<T> {

    Random RANDOM = new Random();

    /**
     * 生成对象
     * @return
     */
    T generator();

}
