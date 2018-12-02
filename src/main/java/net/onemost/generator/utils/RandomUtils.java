package net.onemost.generator.utils;

import java.util.Random;

/**
 * 随机数生成工具
 * @author onemost
 */
public class RandomUtils {

    private RandomUtils(){}

    private static final Random RANDOM = new Random();

    public static Integer integer(){
        return RANDOM.nextInt();
    }

    public static Integer integer(int min){
        return RANDOM.nextInt() + min;
    }

    public static Integer integer(int min, int max){
        return min + RANDOM.nextInt((max - min));
    }

    public static Byte aByte(int min, int max){
        if(min < Byte.MIN_VALUE){
            min = Byte.MIN_VALUE;
        }
        if(max > Byte.MAX_VALUE){
            max = Byte.MAX_VALUE;
        }
        return Byte.valueOf(String.valueOf(integer(min, max)));
    }
}
