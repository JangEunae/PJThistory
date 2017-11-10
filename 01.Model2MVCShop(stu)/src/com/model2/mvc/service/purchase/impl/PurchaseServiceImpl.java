package com.model2.mvc.service.purchase.impl;

import java.util.HashMap;
import java.util.Map;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.dao.PurchaseDAO;


public class PurchaseServiceImpl implements PurchaseService{
	
	private PurchaseDAO purchaseDAO;
	
	public PurchaseServiceImpl() {
		purchaseDAO = new PurchaseDAO();
	}

	public void addPurchase(Purchase purchase) throws Exception {
		purchaseDAO.insertPurchase(purchase);
	}

	
	public Purchase getPurchase(int tranNo) throws Exception {
		return purchaseDAO.findPurchase(tranNo);
	}

	public Purchase getPurchase2(int ProdNo) throws Exception {
		return purchaseDAO.findPurchase2(ProdNo);
	}
		
	public Map<String, Object> getPurchaseList(Search search, String buyerId) throws Exception {
		return purchaseDAO.getPurchaseList(search, buyerId);
	}

	
	public Map<String, Object> getSaleList(Search search) throws Exception {
		return null;
	}

	
	public void updatePurcahse(Purchase purchaseVO) throws Exception {
		purchaseDAO.updatePurchase(purchaseVO);
	}

	
	public void updateTranCode(Purchase purchaseVO) throws Exception {
		purchaseDAO.updateTranCode(purchaseVO);
	}

	public void deleteTranCode(Purchase purchase) throws Exception{
		purchaseDAO.deleteTranCode(purchase);
	}
	

}
