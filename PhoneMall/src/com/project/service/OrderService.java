package com.project.service;

import java.util.List;

import com.project.domain.Order;
import com.project.domain.PageBean;

public interface OrderService {

	void save(Order order) throws Exception;

	PageBean<Order> findMyOrdersByPage(int pageNumber, int pageSize, String uid) throws Exception;

	Order getById(String oid) throws Exception;

	void update(Order order) throws Exception;

	List<Order> findAllByState(String state) throws Exception;

}
