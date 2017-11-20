package com.model2.mvc.service.purchase.test;

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
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.purchase.PurchaseService;
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
public class PurchaseServiceTest {

	//==>@RunWith,@ContextConfiguration 이용 Wiring, Test 할 instance DI
	@Autowired
	@Qualifier("purchaseServiceImpl")
	private PurchaseService purchaseService;

	//@Test
	public void testAddUser() throws Exception {
		
		Purchase purchase =  new Purchase();
		Product product = new Product();
		User user  = new User();
		user.setUserId("user01");
		product.setProdNo(10001);
		purchase.setPurchaseProd(product);
		purchase.setBuyer(user);
		purchase.setPaymentOption("1");
		purchase.setReceiverName("홍길동");
		purchase.setReceiverPhone("01023568956");
		purchase.setDivyRequest("gggg");
		purchase.setTranCode("0  ");
		purchase.setDivyDate("20171120");
		
		
		purchaseService.addPurchase(purchase);
		
		//product = productService.getProduct(product.getProdNo());
		
		Assert.assertEquals("user01", purchase.getBuyer().getUserId());
		Assert.assertEquals(10001, purchase.getPurchaseProd().getProdNo());
		Assert.assertEquals("1", purchase.getPaymentOption());
		Assert.assertEquals("홍길동",purchase.getReceiverName());
		Assert.assertEquals("01023568956", purchase.getReceiverPhone());
		Assert.assertEquals("gggg", purchase.getDivyRequest());
		Assert.assertEquals("0  ", purchase.getTranCode());
		Assert.assertEquals("20171120", purchase.getDivyDate());
	}
	
	//@Test
	public void testGetUser() throws Exception {
		
		Purchase purchase = purchaseService.getPurchase(10027);
		
		//==> 필요하다면...
//			user.setUserId("testUserId");
//			user.setUserName("testUserName");
//			user.setPassword("testPasswd");
//			user.setSsn("1111112222222");
//			user.setPhone("111-2222-3333");
//			user.setAddr("경기도");
//			user.setEmail("test@test.com");
		
		
		//==> API 확인
		Assert.assertEquals("user01", purchase.getBuyer().getUserId());
		Assert.assertEquals(10001, purchase.getPurchaseProd().getProdNo());
		Assert.assertEquals("1  ", purchase.getPaymentOption());
		Assert.assertEquals("홍길동",purchase.getReceiverName());
		Assert.assertEquals("01023568956", purchase.getReceiverPhone());
		Assert.assertEquals("gggg", purchase.getDivyRequest());
		Assert.assertEquals("0  ", purchase.getTranCode());
	}
	
	//@Test
	 public void testUpdateUser() throws Exception{
		
		Purchase purchase = purchaseService.getPurchase(10027);
		
		Assert.assertNotNull(purchase);
		
		purchase.setReceiverName("홍길순");
		purchase.setDivyRequest("헿헤ㅔ헿");
		purchase.setPaymentOption("1  ");
		purchase.setReceiverPhone("01023568956");
		purchase.setDivyRequest("ㅎㅎㅎㅎ");
		
		purchaseService.updatePurcahse(purchase);
		
		purchase = purchaseService.getPurchase(10027);
		
		Assert.assertNotNull(purchase);
		
		//==> console 확인
		//System.out.println(user);
			
		//==> API 확인
		
		
		
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
	 	Map<String,Object> map = purchaseService.getPurchaseList(search, "user01");
	 	
	 	List<Object> list = (List<Object>)map.get("list");
	 	
		//==> console 확인
	 	//System.out.println(list);
	 	
	 	Integer totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 	
	 	System.out.println("=======================================");
	 	
	 	search.setCurrentPage(1);
	 	search.setPageSize(3);
	 	map =  purchaseService.getPurchaseList(search, "user01");
	 	
	 	list = (List<Object>)map.get("list");
	 	
	 	//==> console 확인
	 	//System.out.println(list);
	 	
	 	totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 }
	 
	 //@Test
	 public void testGetUserListByUserId() throws Exception{
		 
		/*Search search = new Search();
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
	 	System.out.println(totalCount);*/
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