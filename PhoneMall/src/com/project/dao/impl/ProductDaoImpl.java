package com.project.dao.impl;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.project.constant.Constant;
import com.project.dao.ProductDao;
import com.project.domain.PageBean;
import com.project.domain.Product;
import com.project.utils.DataSourceUtils;
@SuppressWarnings("all")
public class ProductDaoImpl implements ProductDao {
 
	@Override
	/**
	 * 查询热门商品
	 * */
	public List<Product> findHot() throws Exception {
		QueryRunner qr= new QueryRunner(DataSourceUtils.getDataSource());
		String sql= "select * from product where is_hot = ? and pflag= ? order by pdate desc limit 9";
		return qr.query(sql, new BeanListHandler<>(Product.class), Constant.PRODUCT_IS_HOT, Constant.PRODUCT_IS_UP);
	}

	@Override
	/**
	 * 查询最新商品
	 * */
	public List<Product> findNew() throws Exception {
		QueryRunner qr= new QueryRunner(DataSourceUtils.getDataSource());
		String sql= "select * from product where pflag= ? order by pdate desc limit 9";
		return qr.query(sql, new BeanListHandler<>(Product.class), Constant.PRODUCT_IS_UP);
	}

	@Override
	/**
	 * 查询单个商品
	 * */
	public Product getById(String pid) throws Exception {
		QueryRunner qr=new QueryRunner(DataSourceUtils.getDataSource());
		String sql="select * from product where pid= ? limit 1";
		return qr.query(sql, new BeanHandler<>(Product.class), pid);
	}

	@Override
	/**
	 * 查询当前页数据
	 * */
	public List<Product> findByPage(PageBean<Product> pb, String cid,String pname){
		QueryRunner qr=new QueryRunner(DataSourceUtils.getDataSource());
		//select * from product where cid= ? and pflag= ? order by pdate desc limit ?,?
		String sql="select * from product where pflag= ? ";
		StringBuilder sb = new StringBuilder(sql);
		List params = new ArrayList();
		params.add(Constant.PRODUCT_IS_UP);
		System.out.println(cid);
		if (cid != null && !cid.equals("null")){
			sb.append("and cid = ? ");
			params.add(cid);
		}
		if (pname!=null&&pname.length()>0)
		{
			sb.append("and pname like ? ");
			params.add("%"+pname+"%");
		}
		sb.append("order by pdate desc limit ?,?");
		params.add(pb.getStartIndex());
		params.add(pb.getPageSize());
		System.out.println(sb.toString());
		System.out.println(params);
		try {
			return qr.query(sb.toString(), new BeanListHandler<>(Product.class),params.toArray());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	/**
	 * 获取总记录数 
	 * */
	public int getTotalRecord(String cid,String pname) throws Exception {
		String sql ="select count(*) from product where pflag = ? ";
		StringBuilder sb =new StringBuilder(sql);
		List params = new ArrayList();
		params.add(Constant.PRODUCT_IS_UP);
		if (cid != null && !cid.equals("null")){
			sb.append("and cid = ? ");
			params.add(cid);
		}
		if (pname!=null&&pname.length()>0)
		{
			sb.append("and pname like ? ");
			params.add("%"+pname+"%");
		}
		System.out.println(sb.toString());
		return ((Long)new QueryRunner(DataSourceUtils.getDataSource()).query(sb.toString(), new ScalarHandler(),params.toArray())).intValue();
	}

	@Override
	/**
	 * 后台展示上架商品
	 * */
	public List<Product> findAll() throws Exception {
		QueryRunner qr=new QueryRunner(DataSourceUtils.getDataSource());
		String sql="select * from product where pflag = ? order by pdate desc";
		return qr.query(sql, new BeanListHandler<>(Product.class), Constant.PRODUCT_IS_UP);
	}

	@SuppressWarnings("unused")
	@Override
	/**
	 * 保存商品
	 * */
	public void save(Product p) throws Exception {
		QueryRunner qr=new QueryRunner(DataSourceUtils.getDataSource());
		/*
		 * `pid` varchar(32) NOT NULL,
			  `pname` varchar(50) DEFAULT NULL,
			  `market_price` double DEFAULT NULL,
			  
			  `shop_price` double DEFAULT NULL,
			  `pimage` varchar(200) DEFAULT NULL,
			  `pdate` date DEFAULT NULL,
			  
			  `is_hot` int(11) DEFAULT NULL,
			  `pdesc` varchar(255) DEFAULT NULL,
			  `pflag` int(11) DEFAULT NULL,
			  
			  `cid` varchar(32) DEFAULT NULL,
		  */
		String sql="insert into product values(?,?,?,?,?,?,?,?,?,?);";
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		qr.update(DataSourceUtils.getConnection(),sql,p.getPid(),p.getPname(),p.getMarket_price(),
				p.getShop_price(),p.getPimage(),format.format(p.getPdate()),
				p.getIs_hot(),p.getPdesc(),p.getPflag(),
				p.getCategory().getCid());
		
		
	}

	/*
	 
	 public List<Product> findProductByName(PageBean<Product> p, String pname) throws Exception {
			QueryRunner qr=new QueryRunner(DataSourceUtils.getDataSource());
			String sql="select * from product where pname= ? ";
			return qr.query(sql, new BeanListHandler<>(Product.class), pname,Constant.PRODUCT_IS_UP,p.getStartIndex(),p.getPageSize());
		} */

}
