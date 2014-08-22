package com.first.mybatis.test.test2;

import com.first.mybatis.mapper.IUserMapper;
import com.first.mybatis.pojo.UserPojo;
import com.first.mybatis.test.utils.Log4JUtils;
import com.first.mybatis.test.utils.MapperUtils;

/**
 * Created by pc on 2014/8/21.
 */
public class Test2
{
    public static void main(String[] args) {
        Log4JUtils.loadConfig();

        IUserMapper mapper = MapperUtils.getMapper(IUserMapper.class);

        UserPojo user = mapper.selectUserById(1);

        System.out.println(user);
    }
}
