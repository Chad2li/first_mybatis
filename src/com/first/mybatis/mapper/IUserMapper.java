package com.first.mybatis.mapper;

import com.first.mybatis.pojo.UserPojo;

/**
 * Created by pc on 2014/8/21.
 */
public interface IUserMapper {
    /**
     * 根据用户编号ID查询用户信息
     * @param id
     * @return
     */
    UserPojo selectUserById(int id);
}
