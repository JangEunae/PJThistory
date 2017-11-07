package com.model2.mvc.view.product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
import com.model2.mvc.service.product.vo.ProductVO;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;
import com.model2.mvc.service.user.UserService;
import com.model2.mvc.service.user.impl.UserServiceImpl;
import com.model2.mvc.service.user.vo.UserVO;

public class UpdateProductViewAction extends Action{

	public String execute(	HttpServletRequest request,
												HttpServletResponse response) throws Exception {
		
		String prodNo=request.getParameter("prodNo");
		//System.out.println(prodNo);
		
		ProductService service = new ProductServiceImpl();
		PurchaseService service01 = new PurchaseServiceImpl();
		
		Product product = service.getProduct(Integer.parseInt(prodNo));
		Purchase purchase = service01.getPurchase2(Integer.parseInt(prodNo));
		request.setAttribute("productVO", product);
		request.setAttribute("purchaseVO", purchase);
		
		return "forward:/product/updateProductView.jsp";
		
	}

}
