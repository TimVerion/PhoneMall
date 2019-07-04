package com.project.service;

import java.util.List;

import com.project.domain.PageBean;
import com.project.domain.Product;

public interface ProductService {

	List<Product> findHot() throws Exception;

	List<Product> findNew() throws Exception;

	Product getById(String pid) throws Exception;

	PageBean<Product> findByPage(int pageNumber, int pageSize, String cid, String pname);

	List<Product> findAll() throws Exception;

	void save(Product p) throws Exception;

	//PageBean<Product> findProductByName(int pageNumber, int pageSize, String pname) throws Exception;
	//Product findProductByName(String pname) throws Exception;


}
