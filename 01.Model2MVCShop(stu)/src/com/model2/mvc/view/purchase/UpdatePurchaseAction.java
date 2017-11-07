package com.model2.mvc.view.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;

public class UpdatePurchaseAction extends Action{
	
	public String execute(	HttpServletRequest request,
													HttpServletResponse response) throws Exception {
		
		int tranNo = (Integer.parseInt(request.getParameter("tranNo")));
		
		PurchaseService service = new PurchaseServiceImpl();
		
		//ProductVO productVO = service.getProduct(prodNo);
		Purchase purchase = new Purchase();
		purchase.setTranNo(tranNo);
	
		purchase.setPaymentOption(request.getParameter("paymentOption"));
		purchase.setReceiverName(request.getParameter("receiverName"));
		purchase.setReceiverPhone(request.getParameter("receiverPhone"));
		purchase.setDivyAddr(request.getParameter("receiverAddr"));
		purchase.setDivyRequest(request.getParameter("receiverRequest"));
		purchase.setDivyDate(request.getParameter("divyDate"));
	
		
		service.updatePurcahse(purchase);
		request.setAttribute("purchaseVO", purchase);
	
		
		return "forward:/getPurchase.do?tranNo="+tranNo;//
		
	}

}
