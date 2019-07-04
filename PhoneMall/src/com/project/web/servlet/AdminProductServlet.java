package com.project.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sun.misc.Contended;

import com.project.domain.Product;
import com.project.service.ProductService;
import com.project.utils.BeanFactory;
import com.project.web.servlet.base.BaseServlet;

/**
 * 后台商品模块
 */
public class AdminProductServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	
	/**
	 * 展示已上架商品列表
	 * */
	public String findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//1.调用service 查询已上架商品
			ProductService ps=(ProductService) BeanFactory.getBean("ProductService");
			List<Product> list=ps.findAll();
			
			//2.将返回值放入request中，请求转发
			request.setAttribute("list", list);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		return "admin/product/list";
	}



}
