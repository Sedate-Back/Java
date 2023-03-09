package com.itheima.mapper;

import com.itheima.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {

    // 查询方法
    @Select("select * from tb_user where username = #{username} and password = #{password}")
    User select(@Param("username")String username, @Param("password")String password);


    // 添加用户
    @Insert("INSERT INTO tb_user values (null, #{username}, #{password})")
    void add(User user);

    @Select("select *from tb_user where username= #{uesrname}")
    User selectByUsername(String username);
}
