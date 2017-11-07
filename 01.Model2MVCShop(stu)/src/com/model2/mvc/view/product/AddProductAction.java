package com.model2.mvc.view.product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
import com.model2.mvc.service.product.vo.ProductVO;
import com.model2.mvc.service.user.UserService;
import com.model2.mvc.service.user.impl.UserServiceImpl;

public class AddProductAction  extends Action{
	
	public String execute(	HttpServletRequest request,
														HttpServletResponse response) throws Exception {
		
		Product product = new Product();
		
		product.setProdName(request.getParameter("prodName"));
		product.setProdDetail(request.getParameter("prodDetail"));
		product.setManuDate(request.getParameter("manuDate"));
		product.setPrice(Integer.parseInt(request.getParameter("price")));
		product.setFileName(request.getParameter("fileName"));
		//product.setProTranCode(request.getParameter("proTranCode"));
		/*productVO.setFileName(request.getParameter("fileName"));
		productVO.setManuDate(request.getParameter("manuDate"));
		productVO.setPrice(Integer.parseInt(request.getParameter("price")));
		
		productVO.setProdDetail(request.getParameter("prodDetail"));
		productVO.setProdName(request.getParameter("prodName"));
		productVO.setProdNo(Integer.parseInt(request.getParameter("prodNo")));
		//productVO.setRegDate(request.getParameter("regDate"));
		productVO.setProTranCode(request.getParameter("proTranCode"));*/
		
		
		
		ProductService service = new ProductServiceImpl();
		service.addProduct(product);
		
		request.setAttribute("productVO", product);
		
	
		
		return "forward:/product/addProduct.jsp";//
		
	}

}
