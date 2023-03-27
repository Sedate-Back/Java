package com.itheima.reggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itheima.reggie.entity.Employee;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {
    // TODO 2.继承了BaseMapper 所以常见的赠删改查都在父类中定义好了
}
