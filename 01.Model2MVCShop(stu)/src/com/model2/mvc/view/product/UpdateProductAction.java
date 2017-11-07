package com.model2.mvc.view.product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
import com.model2.mvc.service.product.vo.ProductVO;
import com.model2.mvc.service.user.UserService;
import com.model2.mvc.service.user.impl.UserServiceImpl;
import com.model2.mvc.service.user.vo.UserVO;

public class UpdateProductAction extends Action{
	
	public String execute(	HttpServletRequest request,
													HttpServletResponse response) throws Exception {
		
		int prodNo = (Integer.parseInt(request.getParameter("prodNo")));
		
		ProductService service = new ProductServiceImpl();
		
		//ProductVO productVO = service.getProduct(prodNo);
		Product product = new Product();
		product.setProdNo(prodNo);
	
		product.setProdName(request.getParameter("prodName"));
		product.setProdDetail(request.getParameter("prodDetail"));
		product.setManuDate(request.getParameter("manuDate"));
		product.setPrice(Integer.parseInt(request.getParameter("price")));
		product.setFileName(request.getParameter("fileName"));

		
		
		service.updateProduct(product);
		request.setAttribute("productVO", product);
	
		
		return "forward:/getProduct.do?prodNo="+prodNo;//
		
	}

}
