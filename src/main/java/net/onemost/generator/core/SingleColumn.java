package net.onemost.generator.core;

public interface SingleColumn<T> {

    /**
     * 数据加工
     * @param obj
     * @return
     */
    T execute(T obj);

}
