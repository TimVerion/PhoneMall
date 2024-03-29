package com.project.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.domain.Product;
import com.project.service.ProductService;
import com.project.service.impl.ProductServiceImpl;
import com.project.web.servlet.base.BaseServlet;

/**
 * 首页模块
 */
public class IndexServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	

	public String index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//1.调用productservice查询最新商品和热门商品
			ProductService ps= new ProductServiceImpl();
			List<Product> hotList=ps.findHot();
			List<Product> newList=ps.findNew();
			
			//2.将两个list都放入request域中
			request.setAttribute("hList", hotList);
			request.setAttribute("nList", newList);
		} catch (Exception e) {
		}
		
		return "/jsp/index.jsp";
}
    

}
