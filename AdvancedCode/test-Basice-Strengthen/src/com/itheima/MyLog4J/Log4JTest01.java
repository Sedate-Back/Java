package com.itheima.MyLog4J;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Log4JTest01 {
    private static final Logger LOGGER = LoggerFactory.getLogger(Log4JTest01.class);

    public static void main(String[] args) {
        LOGGER.debug("debug");
        LOGGER.info("info");
        LOGGER.warn("warn");
        LOGGER.error("error");
    }
}

// 因为配置文件中写的只是展示info等级及其以后的等级，所以debug没有被显示出来
