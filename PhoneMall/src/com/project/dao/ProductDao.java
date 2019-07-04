package com.project.dao;

import java.util.List;

import com.project.domain.PageBean;
import com.project.domain.Product;

public interface ProductDao {

	List<Product> findHot() throws Exception;

	List<Product> findNew() throws Exception;

	Product getById(String pid) throws Exception;

	List<Product> findByPage(PageBean<Product> pb, String cid, String pname);

	int getTotalRecord(String cid, String pname) throws Exception;

	List<Product> findAll() throws Exception;

	void save(Product p) throws Exception;

	//List<Product> findProductByName(PageBean<Product> p, String pname) throws Exception;
	
	//Product findProductByName(String name) throws Exception;

}
