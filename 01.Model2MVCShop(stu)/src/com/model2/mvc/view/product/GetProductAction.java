package com.model2.mvc.view.product;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;

public class GetProductAction extends Action{

	public String execute(	HttpServletRequest request,
													HttpServletResponse response) throws Exception {
		
		String prodNo=request.getParameter("prodNo");
		
		ProductService service = new ProductServiceImpl();
		
		Product product = service.getProduct(Integer.parseInt(prodNo));
		
		request.setAttribute("productVO", product);
		
		String str = "";
		Cookie[] cookies = request.getCookies();
		//System.out.println(cookies.length);
		
		if (cookies!=null && cookies.length > 0) {
			for (int i = 0; i < cookies.length; i++) {
				Cookie cookie = cookies[i];
				//System.out.println(cookie.getName());
				if (cookie.getName().equals("history")) {
					str = cookie.getValue();
					//System.out.println(i);
				}
			}
		}
		str += prodNo+",";
		
		Cookie cookie = new Cookie("history", str);
		
		response.addCookie(cookie);
		

		return "forward:/product/getProduct.jsp";
	}

}
