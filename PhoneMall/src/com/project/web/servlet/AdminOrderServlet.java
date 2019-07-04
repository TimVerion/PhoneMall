package com.project.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.constant.Constant;
import com.project.domain.Order;
import com.project.domain.OrderItem;
import com.project.service.OrderService;
import com.project.utils.BeanFactory;
import com.project.utils.JsonUtil;
import com.project.web.servlet.base.BaseServlet;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

/**
 * 后台订单模块
 */
public class AdminOrderServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	
	/**
	 * 修改订单状态
	 * */
	public String updateState(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//1.获取oid
			String oid=request.getParameter("oid");
			
			//2.调用service 获取订单
			OrderService os=(OrderService) BeanFactory.getBean("OrderService");
			Order order=os.getById(oid);
			
			//3.设置订单的状态，更新
			order.setState(Constant.ORDER_YIFAHUO);
			os.update(order);
			
			//4.重定向
			response.sendRedirect(request.getContextPath()+"/adminOrder?method=findAllByState&state=1");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 展示订单详情
	 * */
	public String showDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//0设置编码
			response.setContentType("text/html;charset=utf-8");
			
			//1.获取oid
			String oid=request.getParameter("oid");
		
			//2.调用service 获取订单
			OrderService os=(OrderService) BeanFactory.getBean("OrderService");
			Order order=os.getById(oid);
			
			//3.获取订单的订单项列表 转成json 写回浏览器
			if (order!=null) {
				List<OrderItem> list=order.getItems();
				if (list!=null & list.size()>0) {
					//response.getWriter().println(JsonUtil.list2json(list));
					
					JsonConfig config=JsonUtil.configJson(new String[]{"order","pdate","pdesc","itemid"});
					response.getWriter().println(JSONArray.fromObject(list, config));
				}
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 后台按照状态查询订单列表
	 * */
    public String findAllByState(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	try {
			//1.获取state
   String state=request.getParameter("state");
			
			//2.调用service获取不同的列表
   OrderService os=(OrderService) BeanFactory.getBean("OrderService");
   List<Order> list=os.findAllByState(state);
			
			//3.将list放入request域中，请求转发
   request.setAttribute("list", list);
		} catch (Exception e) {
		}
    	return "/admin/order/list.jsp";
    }

}
