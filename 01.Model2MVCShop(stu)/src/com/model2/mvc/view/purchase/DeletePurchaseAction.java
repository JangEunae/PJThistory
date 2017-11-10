package com.model2.mvc.view.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;
import com.model2.mvc.service.user.UserService;
import com.model2.mvc.service.user.impl.UserServiceImpl;

public class DeletePurchaseAction extends Action{

	public String execute(	HttpServletRequest request,
												HttpServletResponse response) throws Exception {
		
		PurchaseService service = new PurchaseServiceImpl();
		
		Purchase purchase = new Purchase();
		
		String tranNo = request.getParameter("tranNo");
		purchase =service.getPurchase(Integer.parseInt(tranNo));
		
		service.deleteTranCode(purchase);
		request.setAttribute("purchaseVO", purchase);
	
		
		return "forward:/listPurchase.do";	
	}
}
