package com.project.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.project.dao.ProductDao;
import com.project.dao.impl.ProductDaoImpl;
import com.project.domain.PageBean;
import com.project.domain.Product;
import com.project.service.ProductService;
import com.project.utils.BeanFactory;

public class ProductServiceImpl implements ProductService {

	@Override
	/**
	 *查询热门商品
	 * */
	public List<Product> findHot() throws Exception {
		ProductDao pd= (ProductDao) BeanFactory.getBean("ProductDao");
		return pd.findHot();
	}

	@Override
	/**
	 *查询最新商品
	 * */
	public List<Product> findNew() throws Exception {
		//ProductDao pd= (ProductDao) BeanFactory.getBean("ProductDao");
		ProductDao pd= (ProductDao) BeanFactory.getBean("ProductDao");
		return pd.findNew();
	}

	@Override
	/**
	 * 单个商品详情
	 * */
	public Product getById(String pid) throws Exception {
		ProductDao pd= (ProductDao) BeanFactory.getBean("ProductDao");
		return pd.getById(pid);
	}

	@Override
	/**
	 * 分页展示商品
	 * */
	public PageBean<Product> findByPage(int pageNumber, int pageSize, String cid,String pname){
		ProductDao pDao=(ProductDao) BeanFactory.getBean("ProductDao");
		//1.创建pagebean
		PageBean<Product> pb=new PageBean<>(pageNumber,pageSize);
		
		//2.设置当前页数据
		List<Product> data;
		data = pDao.findByPage(pb,cid,pname);
		pb.setData(data);
		
		//3.设置总记录数
		int totalRecord;
		try {
			totalRecord = pDao.getTotalRecord(cid,pname);
			pb.setTotalRecord(totalRecord);
		} catch (Exception e) {
			System.out.println("total出错");
		}
		
		return pb;
	}

	@Override
	/**
	 * 后台展示已上架商品
	 * */
	public List<Product> findAll() throws Exception {
		ProductDao pDao=(ProductDao) BeanFactory.getBean("ProductDao");
		return pDao.findAll();
	}

	@Override
	/**
	 * 保存商品
	 * */
	public void save(Product p) throws Exception {
		ProductDao pDao=(ProductDao) BeanFactory.getBean("ProductDao");
		pDao.save(p);
		
	}
	
	/*
	public PageBean<Product> findProductByName(int pageNumber, int pageSize, String pname) throws Exception {
		ProductDao pDao=(ProductDao) BeanFactory.getBean("ProductDao");
		//1.创建pagebean
		PageBean<Product> p=new PageBean<>(pageNumber,pageSize);
		
		//2.设置当前页数据
		List<Product> data=pDao.findByPage(p,pname);
		p.setData(data);
		
		//3.设置总记录数
		int totalRecord=pDao.getTotalRecord(pname);
		p.setTotalRecord(totalRecord);
		
		return p;
	} */
	
	/**
	 *
	
	public Product findProductByName(String name) throws Exception {
		ProductDao pd= (ProductDao) BeanFactory.getBean("ProductDao");
		
		return pd.findProductByName(name);
	}* */
	

}

