package com.itheima.mapper;



import com.itheima.pojo.Brand;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface BrandMapper {
    // 查询所有
    @Select("select * from tb_brand")
    @ResultMap("brandResultMap")
    List<Brand> selectAll();


    // 添加
    @Insert("insert into tb_brand values (null,#{brandName}, #{companyName}, #{ordered},#{description},#{status})")
    void addAll(Brand brand);

    // 修改时回显内容（id绑定
    @Select("select *from tb_brand where id = #{id}")
    @ResultMap("brandResultMap")
    Brand selectById(int id);


    // 修改
    @Update("update tb_brand set brand_name = #{brandName},company_name = #{companyName},ordered = #{ordered},description = #{description},status = #{status} where id = #{id} ")
    void update(Brand brand);

}
