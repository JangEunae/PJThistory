package com.model2.mvc.service.product.test;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.user.UserService;


/*
 *	FileName :  UserServiceTest.java
 * ㅇ JUnit4 (Test Framework) 과 Spring Framework 통합 Test( Unit Test)
 * ㅇ Spring 은 JUnit 4를 위한 지원 클래스를 통해 스프링 기반 통합 테스트 코드를 작성 할 수 있다.
 * ㅇ @RunWith : Meta-data 를 통한 wiring(생성,DI) 할 객체 구현체 지정
 * ㅇ @ContextConfiguration : Meta-data location 지정
 * ㅇ @Test : 테스트 실행 소스 지정
 */
@RunWith(SpringJUnit4ClassRunner.class)

//==> Meta-Data 를 다양하게 Wiring 하자...
//@ContextConfiguration(locations = { "classpath:config/context-*.xml" })
@ContextConfiguration	(locations = {	"classpath:config/context-common.xml",
																			"classpath:config/context-aspect.xml",
																			"classpath:config/context-mybatis.xml",
																			"classpath:config/context-transaction.xml" })
//@ContextConfiguration(locations = { "classpath:config/context-common.xml" })
public class ProductServiceTest {

	//==>@RunWith,@ContextConfiguration 이용 Wiring, Test 할 instance DI
	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;

	//@Test
	public void testAddUser() throws Exception {
		
		Product product = new Product();
		
		product.setProdName("5번째리팩토링");
		product.setProdDetail("될까");
		product.setManuDate("20171120");
		product.setPrice(2000);
		product.setFileName("shshs");
		
		productService.addProduct(product);
		
		//product = productService.getProduct(product.getProdNo());
		
		Assert.assertEquals("5번째리팩토링", product.getProdName());
		Assert.assertEquals("될까", product.getProdDetail());
		Assert.assertEquals("20171120", product.getManuDate());
		Assert.assertEquals(2000,product.getPrice());
		Assert.assertEquals("shshs", product.getFileName());
	}
	
	//@Test
	public void testGetUser() throws Exception {
		
		Product product = productService.getProduct(10072);
		
		//==> 필요하다면...
//			user.setUserId("testUserId");
//			user.setUserName("testUserName");
//			user.setPassword("testPasswd");
//			user.setSsn("1111112222222");
//			user.setPhone("111-2222-3333");
//			user.setAddr("경기도");
//			user.setEmail("test@test.com");
		
		
		//==> API 확인
		Assert.assertEquals("5번째리팩토링", product.getProdName());
		Assert.assertEquals("될까", product.getProdDetail());
		Assert.assertEquals("20171120", product.getManuDate());
		Assert.assertEquals(2000,product.getPrice());
		Assert.assertEquals("shshs", product.getFileName());

	}
	
	//@Test
	 public void testUpdateUser() throws Exception{
		 
		
		Product product = productService.getProduct(10072);
		
		
		product.setProdName("5번째리팩토링수정22");
		product.setProdDetail("될까???");
		product.setManuDate("20171120");
		product.setPrice(2000);
		
		productService.updateProduct(product);
		
		product = productService.getProduct(10072);
		
		Assert.assertNotNull(product);
		
		//==> console 확인
		//System.out.println(user);
			
		//==> API 확인
		Assert.assertEquals("5번째리팩토링수정22", product.getProdName());
		Assert.assertEquals("될까???", product.getProdDetail());
		Assert.assertEquals("20171120", product.getManuDate());
		Assert.assertEquals(2000,product.getPrice());
		Assert.assertEquals("shshs", product.getFileName());
	 }
	 
	//@Test
	public void testCheckDuplication() throws Exception{

		//==> 필요하다면...
//			User user = new User();
//			user.setUserId("testUserId");
//			user.setUserName("testUserName");
//			user.setPassword("testPasswd");
//			user.setSsn("1111112222222");
//			user.setPhone("111-2222-3333");
//			user.setAddr("경기도");
//			user.setEmail("test@test.com");
//			
//			userService.addUser(user);
		
		//==> console 확인
		//System.out.println(userService.checkDuplication("testUserId"));
		//System.out.println(userService.checkDuplication("testUserId"+System.currentTimeMillis()) );
	 	
		//==> API 확인
		//Assert.assertFalse( userService.checkDuplication("testUserId") );
	 	//Assert.assertTrue( userService.checkDuplication("testUserId"+System.currentTimeMillis()) );
		 	
	}
	
	 //==>  주석을 풀고 실행하면....
	 @Test
	 public void testGetUserListAll() throws Exception{
		 
	 	Search search = new Search();
	 	search.setCurrentPage(1);
	 	search.setPageSize(3);
	 	search.setSearchCondition("2");
	 	search.setSearchKeyword("200000");
	 	Map<String,Object> map = productService.getProductList(search);
	 	System.out.println("들어오고이잇니");
	 	List<Object> list = (List<Object>)map.get("list");
	 	
		//==> console 확인
	 	//System.out.println(list);
	 	
	 	Integer totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 	
	 	System.out.println("=======================================");
	 	
	 }
	 
	 //@Test
	 public void testGetUserListByUserId() throws Exception{
		 
		Search search = new Search();
	 	search.setCurrentPage(1);
	 	search.setPageSize(3);
	 	search.setSearchCondition("0");
	 	search.setSearchKeyword("5");
	 	Map<String,Object> map =  productService.getProductList(search);
	 	
	 	List<Object> list = (List<Object>)map.get("list");
	 	
		//==> console 확인
	 	//System.out.println(list);
	 	
	 	Integer totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 	
	 	System.out.println("=======================================");
	 	
	 	search.setSearchCondition("0");
	 	search.setSearchKeyword(""+System.currentTimeMillis());
	 	map = productService.getProductList(search);
	 	
	 	list = (List<Object>)map.get("list");
	 	
		//==> console 확인
	 	//System.out.println(list);
	 	
	 	totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 }
	 
	// @Test
	 public void testGetUserListByUserName() throws Exception{
		 
	 /*	Search search = new Search();
	 	search.setCurrentPage(1);
	 	search.setPageSize(3);
	 	search.setSearchCondition("1");
	 	search.setSearchKeyword("SCOTT");
	 	Map<String,Object> map = userService.getUserList(search);
	 	
	 	List<Object> list = (List<Object>)map.get("list");
	 	Assert.assertEquals(3, list.size());
	 	
		//==> console 확인
	 	//System.out.println(list);
	 	
	 	Integer totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 	
	 	System.out.println("=======================================");
	 	
	 	search.setSearchCondition("1");
	 	search.setSearchKeyword(""+System.currentTimeMillis());
	 	map = userService.getUserList(search);
	 	
	 	list = (List<Object>)map.get("list");
	 	Assert.assertEquals(0, list.size());
	 	
		//==> console 확인
	 	//System.out.println(list);
	 	
	 	totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);*/
	 }	 
}