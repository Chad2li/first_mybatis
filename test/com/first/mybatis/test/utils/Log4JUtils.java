package com.first.mybatis.test.utils;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * Created by pc on 2014/8/21.
 */
public class Log4JUtils
{
    private static Logger log = Logger.getLogger(Log4JUtils.class);

    public static void loadConfig()
    {
        String projectPath = Log4JUtils.class.getResource("/").getPath();

        PropertyConfigurator.configure(projectPath + "/log4j.properties");
    }

    public static void main(String[] args) {
        loadConfig();
        log.info("log4j load success!");
    }

}
