package com.cnm;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogTest
{
    private static  final Logger log= LoggerFactory.getLogger(LogTest.class);
    @Test
    public void testLog()
    {
        log.debug("开始打印");
        int i =1+1;
        System.out.println("咕咕嘎嘎");
        log.info(String.valueOf(i));
        log.debug("结束打印");
        log.trace("trace");
        log.error("error");
    }
}
