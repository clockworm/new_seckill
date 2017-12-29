package com.bioodas.seckill.util;

import java.util.Random;

/**
 * @author TangLingYun
 * @describe 主键生成策略
 * @date 2017年12月29日
 */
public class KeyUtil {

    public static synchronized String genUniqueKey(){
        Random random = new Random();
        Integer number = random.nextInt(900000) + 100000;
        return System.currentTimeMillis() + String.valueOf(number);
    }

}