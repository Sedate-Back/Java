package com.itheima.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itheima.reggie.entity.Employee;
import com.itheima.reggie.mapper.EmployeeMapper;
import com.itheima.reggie.service.EmployeeService;
import org.springframework.stereotype.Service;

// TODO 4. 创建Service实现类 继承ServiceImpl接口，实现服务类接口方法
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {
}
