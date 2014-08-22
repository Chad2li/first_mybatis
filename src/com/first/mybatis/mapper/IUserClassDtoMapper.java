package com.first.mybatis.mapper;

import com.first.mybatis.dto.UserClassDto;
import com.first.mybatis.dto.UserClassDto2;

/**
 * Created by pc on 2014/8/20.
 */
public interface IUserClassDtoMapper {
    /**
     * 调用存储过程返回相应用户ID的用户及班级信息
     * @param id
     * @param classid
     * @return
     */
    UserClassDto selectUserAndClass(int id, int classid);

    /**
     * 调用存储过程返回相应用户ID的用户及班级信息 2
     * @param id
     * @param classid
     * @return
     */
    UserClassDto2 selectUserAndClass2(int id, int classid);
}
