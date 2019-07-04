package com.project.service.impl;

import java.util.List;

import com.project.dao.OrderDao;
import com.project.domain.Order;
import com.project.domain.OrderItem;
import com.project.domain.PageBean;
import com.project.service.OrderService;
import com.project.utils.BeanFactory;
import com.project.utils.DataSourceUtils;

public class OrderServiceImpl implements OrderService {

	@Override
	/**
	 * 保存订单
	 * */
	public void save(Order order) throws Exception {
		try {
			// 获取dao
			OrderDao od=(OrderDao) BeanFactory.getBean("OrderDao");
			//1.开启事物
			DataSourceUtils.startTransaction();
			
			//2.向orders表中插入一条
			od.save(order);
			
			//3.向orderitem表中插入n条
			for (OrderItem oi : order.getItems()) {
				od.saveItem(oi);
			}
			//4.事物控制
			DataSourceUtils.commitAndClose();
		} catch (Exception e) {
			e.printStackTrace();
			DataSourceUtils.rollbackAndClose();
			throw e;
		}
		
	}

	/**
	 * 我的订单
	 * */
	public PageBean<Order> findMyOrdersByPage(int pageNumber, int pageSize, String uid) throws Exception {
		OrderDao od=(OrderDao) BeanFactory.getBean("OrderDao");
		
		//1.创建pagebean
		PageBean<Order> pb=new PageBean<>(pageNumber,pageSize);
		
		//2.查询总条数 设置总条数
		int totalRecord=od.getTotalRecord(uid);
		pb.setTotalRecord(totalRecord);
		
		//3.查询当前页数据 设置当前页数据
		List<Order> data=od.findMyOrdersByPage(pb,uid);
		pb.setData(data);
		return pb;
	}

	@Override
	/**
	 * 订单详情
	 * */
	public Order getById(String oid) throws Exception {
		OrderDao od=(OrderDao) BeanFactory.getBean("OrderDao");
		return od.getById(oid);
	}

	@Override
	/**
	 * 修改订单
	 * */
	public void update(Order order) throws Exception {
		OrderDao od=(OrderDao) BeanFactory.getBean("OrderDao");
		od.update(order);
		
	}

	@Override
	/**
	 * 后台查询订单列表
	 * */
	public List<Order> findAllByState(String state) throws Exception {
		OrderDao od=(OrderDao) BeanFactory.getBean("OrderDao");
		return od.findAllByState(state);
	}

}
