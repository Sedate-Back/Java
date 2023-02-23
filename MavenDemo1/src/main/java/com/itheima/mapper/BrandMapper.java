package com.itheima.mapper;

import com.itheima.pojo.Brand;
import org.apache.ibatis.annotations.Param;


import java.util.List;

public interface BrandMapper {

    // 查询所有。放回列表
    List<Brand> selectAll();

    // 根据Id查询，返回查询到的id信息
    Brand selectById(int id);

    // 多条件查询，返回所有结果（还有2种方式，利用brand对象和Map集合的方式)
    List<Brand> selectByCondition(@Param("status") int status, @Param("companyName") String companyName, @Param("brandName") String brandName);

    // 单条件动态查询，返回Brand对象
    List<Brand> selectSingleByCondition(Brand brand);

    // 添加数据
    void addAll(Brand brand);

    // 添加数据，主键返回：就是可以查询添加的数据后，id是多少，便于后续操作
    void addReturnId(Brand brand);


    // 修改数据
    void update(Brand brand);

    // 删除数据
    void deleteById(int id);


    // 批量删除数据
    void deleteByIds(@Param("ids") int[] ids);
}
