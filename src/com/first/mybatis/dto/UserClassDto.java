package com.first.mybatis.dto;

import com.first.mybatis.pojo.ClassPojo;
import com.first.mybatis.pojo.UserPojo;

/**
 * Created by pc on 2014/8/20.
 */
public class UserClassDto
{
    private UserPojo user;

    private ClassPojo clazz;

    public UserPojo getUser() {
        return user;
    }

    public void setUser(UserPojo user) {
        this.user = user;
    }

    public ClassPojo getClazz() {
        return clazz;
    }

    public void setClazz(ClassPojo clazz) {
        this.clazz = clazz;
    }

    public UserClassDto() { super(); }

    @Override
    public String toString() {
        return "UserClassDto{" +
                "user=" + user +
                ", clazz=" + clazz +
                '}';
    }
}
