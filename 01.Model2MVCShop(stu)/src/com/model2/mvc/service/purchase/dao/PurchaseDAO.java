package com.model2.mvc.service.purchase.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.model2.mvc.common.Search;
import com.model2.mvc.common.util.DBUtil;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.user.UserService;
import com.model2.mvc.service.user.impl.UserServiceImpl;



public class PurchaseDAO {
	
	public PurchaseDAO() {
		
	}
	
	public void insertPurchase(Purchase purchase) throws Exception {
		
		
		System.out.println("여기는 DAO");
		System.out.println(purchase);
		Connection con = DBUtil.getConnection();
		
		String sql = "insert into TRANSACTION values (seq_transaction_tran_no.nextval,?,?,?,?,?,?,?,?,sysdate,?)";
		
		PreparedStatement stmt = con.prepareStatement(sql);
		
		stmt.setInt(1, purchase.getPurchaseProd().getProdNo());
		stmt.setString(2, purchase.getBuyer().getUserId());
		stmt.setString(3, purchase.getPaymentOption());
		stmt.setString(4, purchase.getReceiverName());
		stmt.setString(5, purchase.getReceiverPhone());
		stmt.setString(6, purchase.getDivyAddr());
		stmt.setString(7, purchase.getDivyRequest());
		stmt.setString(8, purchase.getTranCode());
		stmt.setString(9, purchase.getDivyDate());
		stmt.executeUpdate();
		
		con.close();		
	}
	
	public Purchase findPurchase(int tranNo) throws Exception {
		
		Connection con = DBUtil.getConnection();
		
		String sql = "select * from TRANSACTION t, USERS u where TRAN_NO=? and t.BUYER_ID = u.USER_ID ";
		
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setInt(1, tranNo);
		
		ResultSet rs = stmt.executeQuery();

		Purchase purchase = null;
		Product product = null;
		User user = null;
		
		while (rs.next()) {
			purchase = new Purchase();
			product = new Product();
			user = new User();
			user.setUserId(rs.getString("BUYER_ID"));
			product.setProdNo(rs.getInt("PROD_NO"));
			purchase.setPurchaseProd(product);
			purchase.setBuyer(user);
			purchase.setTranNo(rs.getInt("TRAN_NO"));
			purchase.setPaymentOption(rs.getString("PAYMENT_OPTION"));
			purchase.setReceiverName(rs.getString("RECEIVER_NAME"));
			purchase.setReceiverPhone(rs.getString("RECEIVER_PHONE"));
			purchase.setDivyAddr(rs.getString("DEMAILADDR"));
			purchase.setDivyRequest(rs.getString("DLVY_REQUEST"));
			purchase.setDivyDate(rs.getString("DLVY_DATE"));
			purchase.setTranCode(rs.getString("TRAN_STATUS_CODE"));
		}
		
	
		con.close();

		return purchase;
		
	}
	public Purchase findPurchase2(int ProdNo) throws Exception {
		
		
		Connection con = DBUtil.getConnection();
		
		String sql = "select * from TRANSACTION t, USERS u where PROD_NO=? and t.BUYER_ID = u.USER_ID ";
		
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setInt(1, ProdNo);
		
		ResultSet rs = stmt.executeQuery();

		Purchase purchase = null;
		Product product = null;
		User user = null;
		
		while (rs.next()) {
			purchase = new Purchase();
			user = new User();
			product = new Product();
			product.setProdNo(rs.getInt("PROD_NO"));
			user.setUserId(rs.getString("BUYER_ID"));
			purchase.setPurchaseProd(product);
			purchase.setBuyer(user);
			purchase.setTranNo(rs.getInt("TRAN_NO"));
			purchase.setPaymentOption(rs.getString("PAYMENT_OPTION"));
			purchase.setReceiverName(rs.getString("RECEIVER_NAME"));
			purchase.setReceiverPhone(rs.getString("RECEIVER_PHONE"));
			purchase.setDivyAddr(rs.getString("DEMAILADDR"));
			purchase.setDivyRequest(rs.getString("DLVY_REQUEST"));
			purchase.setDivyDate(rs.getString("DLVY_DATE"));
			purchase.setTranCode(rs.getString("TRAN_STATUS_CODE"));
		}
		
		
		con.close();

		return purchase;	
	}
	public Map<String,Object> getPurchaseList(Search search, String buyerId) throws Exception{
		
		Map<String , Object>  map = new HashMap<String, Object>();
		
		Connection con = DBUtil.getConnection();
		
		String sql = "select * from TRANSACTION t, USERS u  where BUYER_ID='"+buyerId+"'and t.BUYER_ID = u.USER_ID ORDER BY ORDER_DATA DESC";
		/*if (searchVO.getSearchCondition() != null) {
			if (searchVO.getSearchCondition().equals("0")) {
				sql += " where TRAN_NO='" + searchVO.getSearchKeyword()
						+ "'";
			} else if (searchVO.getSearchCondition().equals("1")) {
				sql += " where PROD_NO='" + searchVO.getSearchKeyword()
						+ "'";
			}
		}*/
		
		System.out.println("PurchaseDAO::Original SQL :: " + sql);
		
		
		//==> TotalCount GET
		int totalCount = this.getTotalCount(sql);
		System.out.println("PurchaseDAO :: totalCount  :: " + totalCount);
		
		//==> CurrentPage 게시물만 받도록 Query 다시구성
		sql = makeCurrentPageSql(sql, search);
		PreparedStatement pStmt = con.prepareStatement(sql);
		//pStmt.setString(1, buyerId);
		ResultSet rs = pStmt.executeQuery();
	
		System.out.println(search);

		List<Purchase> list = new ArrayList<Purchase>();
		List<User> list02 = new ArrayList<User>();
		while(rs.next()){
			Purchase purchase = new Purchase();
			User user = new User();
			user.setUserId(buyerId);
			purchase.setBuyer(user);
			purchase.setTranNo(rs.getInt("TRAN_NO"));
			purchase.setPaymentOption(rs.getString("PAYMENT_OPTION"));
			purchase.setReceiverName(rs.getString("RECEIVER_NAME"));
			purchase.setReceiverPhone(rs.getString("RECEIVER_PHONE"));
			purchase.setDivyAddr(rs.getString("DEMAILADDR"));
			purchase.setDivyRequest(rs.getString("DLVY_REQUEST"));
			purchase.setDivyDate(rs.getString("DLVY_DATE"));
			purchase.setTranCode(rs.getString("TRAN_STATUS_CODE"));
			list.add(purchase);
			list02.add(user);
			
		}
		
		//==> totalCount 정보 저장
		map.put("totalCount", new Integer(totalCount));
		//==> currentPage 의 게시물 정보 갖는 List 저장
		map.put("list", list);
		map.put("list02", list02);
		
		rs.close();
		pStmt.close();
		con.close();

		return map;
	}

	
	public Map<String,Object> getSaleList(Search search, String buyerId) throws Exception{ 
	
		return null;
	}
	
	public void updatePurchase(Purchase purchase) throws Exception {
		
		Connection con = DBUtil.getConnection();

		String sql = "update TRANSACTION  set PAYMENT_OPTION=?,RECEIVER_NAME=?,RECEIVER_PHONE=?,DEMAILADDR=?,DLVY_REQUEST=?,DLVY_DATE=?   "
				+ "where TRAN_NO=?  ";
		
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, purchase.getPaymentOption());
		stmt.setString(2, purchase.getReceiverName());
		stmt.setString(3, purchase.getReceiverPhone());
		stmt.setString(4, purchase.getDivyAddr());
		stmt.setString(5, purchase.getDivyRequest());
		stmt.setString(6, purchase.getDivyDate());
		stmt.setInt(7, purchase.getTranNo());
		
		stmt.executeUpdate();
		
		con.close();
	}
	
	public void updateTranCode(Purchase purchase) throws Exception {
		
		Connection con = DBUtil.getConnection();
		
		String sql = "update TRANSACTION  set TRAN_STATUS_CODE=?   "
				+ "where TRAN_NO=?  ";
		
		PreparedStatement stmt = con.prepareStatement(sql);
		System.out.println("여기인가"+purchase);
		
		String tranCode  = null;
		if(purchase.getTranCode().equals("0  ")) {
			tranCode = "1  ";
		}else if(purchase.getTranCode().equals("1  ")) {
			tranCode = "2  ";
		}else {
			tranCode = "3  ";
		}
		
		stmt.setString(1, tranCode);
		stmt.setInt(2, purchase.getTranNo());
		
		stmt.executeUpdate();
		
		con.close();
		
	}
	
private int getTotalCount(String sql) throws Exception {
		
		sql = "SELECT COUNT(*) "+
		          "FROM ( " +sql+ ") countTable";
		
		Connection con = DBUtil.getConnection();
		PreparedStatement pStmt = con.prepareStatement(sql);
		ResultSet rs = pStmt.executeQuery();
		
		int totalCount = 0;
		if( rs.next() ){
			totalCount = rs.getInt(1);//column수를 세어주는 식,(테이블의 첫번째 항목의 수를 센다)
		}
		
		pStmt.close();
		con.close();
		rs.close();
		
		return totalCount;
	}
	
	// 게시판 currentPage Row 만  return 
	private String makeCurrentPageSql(String sql , Search search){
		sql = 	"SELECT * "+ 
					"FROM (		SELECT inner_table. * ,  ROWNUM AS row_seq " +
									" 	FROM (	"+sql+" ) inner_table "+
									"	WHERE ROWNUM <="+search.getCurrentPage()*search.getPageSize()+" ) " +
					"WHERE row_seq BETWEEN "+((search.getCurrentPage()-1)*search.getPageSize()+1) +" AND "+search.getCurrentPage()*search.getPageSize();
		
		System.out.println("UserDAO :: make SQL :: "+ sql);	
		
		return sql;
	}
	
	
	
}
