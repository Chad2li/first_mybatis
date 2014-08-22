package com.first.mybatis.test.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by pc on 2014/8/20.
 */
public class MapperUtils
{
    public static <T>  T getMapper(Class<T> c)
    {
        String conf = "mybatis-conf.xml";
        InputStream in = null;
        try {
            in = Resources.getResourceAsStream(conf);
        } catch (IOException e) {
            e.printStackTrace();
        }

        SqlSessionFactory ssf = new SqlSessionFactoryBuilder().build(in);

        SqlSession ss = ssf.openSession();

        return ss.getMapper(c);
    }
}
