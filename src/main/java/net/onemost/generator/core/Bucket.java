package net.onemost.generator.core;

import net.onemost.generator.strategy.BaseStrategy;

import java.util.ArrayList;
import java.util.List;

/**
 * @author onemost
 */
public class Bucket {

    private Bucket(){}

    private List<Strategy> list;

    private long num;

    private String tableName;

    public static Bucket create(String tableName) {
        Bucket bucket = new Bucket();
        bucket.tableName = tableName;
        bucket.list = new ArrayList<>();
        bucket.num = 1;
        return bucket;
    }

    public Bucket setNum(long num){
        this.num = num;
        return this;
    }

    protected  String getTableName(){
        return this.tableName;
    }

    protected  long getNum(){
        return this.num;
    }

    protected  List<Strategy> getStrategy(){
        return this.list;
    }


    /**
     * 新增规则字段
     * @param fieldName
     * @param result
     * @param <T>
     * @return
     */
    public <T> Strategy<T> addFiled(String fieldName, Class<T> result){
        return Strategy.setField(fieldName, result, this);
    }

    public static class Strategy<T>{

        private Strategy(){}

        protected BaseStrategy<T> baseStrategy;

        protected String filed;

        protected Class<T> clazz;

        protected  boolean allowRepeat = true;

        protected Bucket self;

        protected String otherTable;

        protected String otherColumn;

        protected SingleColumn<T> otherColumnHandler;


        protected static <T> Strategy<T>  setField(String filedName, Class<T> resultType, Bucket self){
            Strategy<T> strategy = new Strategy<T>();
            strategy.filed = filedName;
            strategy.self = self;
            strategy.clazz = resultType;
            return strategy;
        }

        public Strategy<T> setOtherTable(String otherTableName, String otherColumn){
            this.otherTable = otherTableName;
            this.otherColumn = otherColumn;
            self.list.add(this);
            return this;
        }

        public Bucket singleColumn(SingleColumn<T> otherColumnHandler){
            this.otherColumnHandler = otherColumnHandler;
            return self;
        }

        public Strategy<T> allowRepeat(boolean allow){
            this.allowRepeat = allow;
            return this;
        }

        public Bucket setStrategy(BaseStrategy<T> baseStrategy){
            this.baseStrategy = baseStrategy;
            self.list.add(this);
            return self;
        }

        protected  String getFiled(){
            return this.filed;
        }
    }
}
