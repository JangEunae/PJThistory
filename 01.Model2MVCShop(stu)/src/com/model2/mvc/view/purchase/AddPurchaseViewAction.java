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
import com.model2.mvc.service.purchase.vo.PurchaseVO;
import com.model2.mvc.service.user.UserService;
import com.model2.mvc.service.user.impl.UserServiceImpl;

public class AddPurchaseViewAction extends Action{
	
	public String execute(	HttpServletRequest request,
													HttpServletResponse response) throws Exception {

		String prodNo = request.getParameter("prodNo");
		
		ProductService service = new ProductServiceImpl();
	
		Product product = service.getProduct(Integer.parseInt(prodNo));
		
		String userId = request.getParameter("userId");
		
		UserService service01 = new UserServiceImpl();
		User user = service01.getUser(userId);
	
		request.setAttribute("userVO", user);
		request.setAttribute("productVO", product);
		
		return "forward:/purchase/addPurchaseView.jsp";
	}
}
