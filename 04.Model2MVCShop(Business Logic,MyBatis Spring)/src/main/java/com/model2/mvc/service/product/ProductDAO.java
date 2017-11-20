package com.model2.mvc.service.product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import com.model2.mvc.common.Search;
import com.model2.mvc.common.util.DBUtil;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.User;

public interface ProductDAO {
	
	public void addProduct(Product product) throws Exception;
	
	public Product getProduct(int prodNo) throws Exception ;
	
	public List<Product> getProductList(Search search) throws Exception;

	public void updateProduct(Product product) throws Exception ;
	
	public int getTotalCount(Search search) throws Exception ;
	
}

