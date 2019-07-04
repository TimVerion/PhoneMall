package com.project.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.domain.PageBean;
import com.project.domain.Product;
import com.project.service.ProductService;
import com.project.service.impl.ProductServiceImpl;
import com.project.web.servlet.base.BaseServlet;

/**
 * 前台商品模块
 */
public class ProductServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	
	/**
	 * 前台搜索框，根据传入的name，进行查询，将结果返回到商品详情页
	
	public String findProductByName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
		    int pageNumber=1;
		    int pageSize=12;
			// 接收传过来的name
			String pname=request.getParameter("pname");
			//调用业务层，执行通过id查询商品信息
			ProductService ps=new ProductServiceImpl();
			PageBean<Product> bean=ps.findProductByName(pageNumber,pageSize,pname);
			//如果成功查询出商品信息，将p放入
			request.setAttribute("p", bean);
		} catch (Exception e) {
			request.setAttribute("msg", "商品查询失败");
			return "/jsp/msg.jsp";
		}
		return "/jsp/product_list.jsp";
	}  * */
	
	/**
	 * 分类商品分页展示
	 * */
	public String findByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// 1.获取pagenumber cid 设置pagesize
			String pname =request.getParameter("pname");
			int pageNumber=1;
			
			try {
				pageNumber=Integer.parseInt(request.getParameter("pageNumber"));
			} catch (NumberFormatException e) {
			}
			
			int pageSize=12;
			String cid=request.getParameter("cid");
			
			//2.调用service 分页查询商品 参数：3个，返回值pagebean
			ProductService ps=new ProductServiceImpl();
			PageBean<Product> bean=ps.findByPage(pageNumber,pageSize,cid,pname);
			request.setAttribute("pname", pname);
			//3.pagebean放入request中，请求转发product_list.jsp
			request.setAttribute("pb", bean);
		} catch (Exception e) {
			request.setAttribute("msg", "分页查询失败");
			return "/jsp/msg.jsp";
		}
		return "/jsp/product_list.jsp";
	}
	
	/**
	 * 商品详情
	 */
	
	public String getById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// 1.获取pid
			String pid=request.getParameter("pid");
			
			//2.调用service获取单个商品 参数：pid 返回值：product
			ProductService ps=new ProductServiceImpl();
			Product pro=ps.getById(pid);
			
			//3.将product放入request域中，请求转发到/jsp/prodcut_info.jsp
			request.setAttribute("bean", pro);
		} catch (Exception e) {
			request.setAttribute("msg", "查询单个商品失败");
			return "/jsp/msg.jsp";
		}
		return "/jsp/product_info.jsp";
	} 

    
}
