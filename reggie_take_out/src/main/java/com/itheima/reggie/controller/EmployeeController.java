package com.itheima.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itheima.reggie.common.R;
import com.itheima.reggie.entity.Employee;
import com.itheima.reggie.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.jdbc.Null;
import org.apache.tomcat.util.digester.Digester;
import org.springframework.beans.factory.BeanCurrentlyInCreationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;


// TODO 5, 创建Controller类 执行方法
@Slf4j
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    // TODO 8. 定义登录方法
    @PostMapping("/login")
    public R<Employee> login(HttpServletRequest request, @RequestBody Employee employee){
        //
        String password = employee.getPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes());

        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Employee::getUsername, employee.getUsername());
        Employee emp = employeeService.getOne(queryWrapper);

        if(emp == null){
            return R.error("登陆失败，查询不到用户名");
        }


        if(!emp.getPassword().equals(password)){
            return R.error("密码输入错误，请重新输入");
        }

        if(emp.getStatus() == 0){
            return R.error("账号已锁定！");
        }

        request.getSession().setAttribute("employee", emp.getId());
        return R.success(emp);
    }

    // TODO 9. 定义退出方法
    @PostMapping("/logout")
    public R<String> logout(HttpServletRequest request){
        // 清理session
        request.getSession().removeAttribute("employee");
        return R.success("退出成功！");
    }

    @PostMapping
    public R<String> save(HttpServletRequest request, @RequestBody Employee employee){
        log.info("新增员工，员工信息{}", employee.toString());
        //设置初始密码123456，需要进行md5加密处理
        employee.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));

        // 设置账号创建时间
        employee.setCreateTime(LocalDateTime.now());
        employee.setUpdateTime(LocalDateTime.now());

        //获得当前登录用户的id
        Long empId = (Long) request.getSession().getAttribute("employee");

        employee.setCreateUser(empId);
        employee.setUpdateUser(empId);

        employeeService.save(employee);
        return R.success("新增员工成功");
    }

    /**
     * 员工信息分页查询
     * @param page 当前查询页码
     * @param pageSize 每页展示记录数
     * @param name 员工姓名 - 可选参数
     * @return
     */
    @GetMapping("/page")
    public R<Page> page(int page, int pageSize, String name){
        log.info("page = {},pageSize = {},name = {}" ,page,pageSize,name);
        //构造分页构造器
        Page pageInfo = new Page(page,pageSize);

        //构造条件构造器
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper();
        //添加过滤条件
        queryWrapper.like(StringUtils.isNotEmpty(name),Employee::getName,name);
        //添加排序条件
        queryWrapper.orderByDesc(Employee::getUpdateTime);

        //执行查询
        employeeService.page(pageInfo,queryWrapper);
        return R.success(pageInfo);
    }
    /**
     * 根据id修改员工信息
     * @param employee
     * @return
     */
    @PutMapping
    public R<String> update(HttpServletRequest request,@RequestBody Employee employee){
        log.info(employee.toString());

        Long empId = (Long)request.getSession().getAttribute("employee");

        employee.setUpdateTime(LocalDateTime.now());
        employee.setUpdateUser(empId);
        employeeService.updateById(employee);

        return R.success("员工信息修改成功");
    }

    /**
     * 根据id查询员工信息
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public R<Employee> getById(@PathVariable Long id){
        log.info("根据id查询员工信息...");
        Employee employee = employeeService.getById(id);
        if(employee != null){
            return R.success(employee);
        }
        return R.error("没有查询到对应员工信息");
    }
}
