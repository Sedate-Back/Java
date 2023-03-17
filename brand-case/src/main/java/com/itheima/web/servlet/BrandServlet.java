package com.itheima.web.servlet;

import com.alibaba.fastjson.JSON;
import com.itheima.pojo.Brand;
import com.itheima.pojo.PageBean;
import com.itheima.service.BrandService;
import com.itheima.service.impl.BrandServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import java.util.function.IntPredicate;

@WebServlet("/brand/*")
public class BrandServlet extends BaseServlet {

    private BrandService brandService = new BrandServiceImpl();

    //用户实现分页查询
    public void selectAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Brand> brands = brandService.selectAll();

        // 转为Json
        String jsonString = JSON.toJSONString(brands);

        // 写数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    //添加企业信息
    public void addAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
        BufferedReader bufferedReader = request.getReader();
        String params = bufferedReader.readLine(); // params是JSON格式的

        // 转化格式
        Brand brand = JSON.parseObject(params, Brand.class);

        brandService.addAll(brand);

        response.getWriter().write("success");
    }

    //修改企业信息
    public void update(HttpServletRequest req, HttpServletResponse resp) {}

    //删除企业信息
    public void deleteByIds(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 获取Post请求传递的ids列表
        BufferedReader bufferedReader = request.getReader();
        String params = bufferedReader.readLine(); // params是JSON格式的


        int[] ids = JSON.parseObject(params, int[] .class);
        System.out.println(ids);
        //brandService.deleteByIds(params);

        response.getWriter().write("success");

    }

    // 分页查询
    public void selectByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1. 接收 当前页码 和 每页展示条数    url?currentPage=1&pageSize=5 get请求
        String _currentPage = request.getParameter("currentPage");
        String _pageSize = request.getParameter("pageSize");

        // 类型转换
        int currentPage = Integer.parseInt(_currentPage);
        int pageSize = Integer.parseInt(_pageSize);

        //2. 调用service查询
        PageBean<Brand> pageBean = brandService.selectByPage(currentPage, pageSize);

        //2. 转为JSON
        String jsonString = JSON.toJSONString(pageBean);
        //3. 写数据 - response
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    /**
     * 分页条件查询
     * @param request
     * @param response
     **/

    public void selectByPageAndCondition(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //1. 接收 当前页码 和 每页展示条数    url?currentPage=1&pageSize=5
        String _currentPage = request.getParameter("currentPage");
        String _pageSize = request.getParameter("pageSize");

        int currentPage = Integer.parseInt(_currentPage);
        int pageSize = Integer.parseInt(_pageSize);

        // 获取查询条件对象 => post过来的对象
        BufferedReader br = request.getReader();
        String params = br.readLine();//json字符串

        //转为 Brand
        Brand brand = JSON.parseObject(params, Brand.class);
        // System.out.println(brand);
        // System.out.println(currentPage);
        // System.out.println(pageSize);

        //2. 调用service查询
        PageBean<Brand> pageBean = brandService.selectByPageAndCondition(currentPage,pageSize,brand);
        System.out.println(pageBean.toString());
        //2. 转为JSON
        String jsonString = JSON.toJSONString(pageBean);
        //3. 写数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

}
