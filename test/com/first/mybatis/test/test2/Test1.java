package com.first.mybatis.test.test2;

import com.first.mybatis.dto.UserClassDto;
import com.first.mybatis.dto.UserClassDto2;
import com.first.mybatis.mapper.IUserClassDtoMapper;
import com.first.mybatis.test.utils.Log4JUtils;
import com.first.mybatis.test.utils.MapperUtils;

/**
 * Created by pc on 2014/8/20.
 */
public class Test1
{
    public static void main(String[] args)
    {
        Log4JUtils.loadConfig();

        IUserClassDtoMapper mapper = MapperUtils.getMapper(IUserClassDtoMapper.class);

        UserClassDto2 dto = mapper.selectUserAndClass2(1, 1);

        System.out.println(dto);
    }
}
