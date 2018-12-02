package net.onemost.generator.core;

/**
 * 多行处理
 * @param <T>
 */
public interface MultiColumn<T> {

    /**
     * 数据处理
     * @param objects
     * @return
     */
    T dataHandler(Object... objects);

}