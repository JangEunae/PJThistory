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
 * �� JUnit4 (Test Framework) �� Spring Framework ���� Test( Unit Test)
 * �� Spring �� JUnit 4�� ���� ���� Ŭ������ ���� ������ ��� ���� �׽�Ʈ �ڵ带 �ۼ� �� �� �ִ�.
 * �� @RunWith : Meta-data �� ���� wiring(����,DI) �� ��ü ����ü ����
 * �� @ContextConfiguration : Meta-data location ����
 * �� @Test : �׽�Ʈ ���� �ҽ� ����
 */
@RunWith(SpringJUnit4ClassRunner.class)

//==> Meta-Data �� �پ��ϰ� Wiring ����...
//@ContextConfiguration(locations = { "classpath:config/context-*.xml" })
@ContextConfiguration	(locations = {	"classpath:config/context-common.xml",
																			"classpath:config/context-aspect.xml",
																			"classpath:config/context-mybatis.xml",
																			"classpath:config/context-transaction.xml" })
//@ContextConfiguration(locations = { "classpath:config/context-common.xml" })
public class PurchaseServiceTest {

	//==>@RunWith,@ContextConfiguration �̿� Wiring, Test �� instance DI
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
		purchase.setReceiverName("ȫ�浿");
		purchase.setReceiverPhone("01023568956");
		purchase.setDivyRequest("gggg");
		purchase.setTranCode("0  ");
		purchase.setDivyDate("20171120");
		
		
		purchaseService.addPurchase(purchase);
		
		//product = productService.getProduct(product.getProdNo());
		
		Assert.assertEquals("user01", purchase.getBuyer().getUserId());
		Assert.assertEquals(10001, purchase.getPurchaseProd().getProdNo());
		Assert.assertEquals("1", purchase.getPaymentOption());
		Assert.assertEquals("ȫ�浿",purchase.getReceiverName());
		Assert.assertEquals("01023568956", purchase.getReceiverPhone());
		Assert.assertEquals("gggg", purchase.getDivyRequest());
		Assert.assertEquals("0  ", purchase.getTranCode());
		Assert.assertEquals("20171120", purchase.getDivyDate());
	}
	
	//@Test
	public void testGetUser() throws Exception {
		
		Purchase purchase = purchaseService.getPurchase(10027);
		
		//==> �ʿ��ϴٸ�...
//			user.setUserId("testUserId");
//			user.setUserName("testUserName");
//			user.setPassword("testPasswd");
//			user.setSsn("1111112222222");
//			user.setPhone("111-2222-3333");
//			user.setAddr("��⵵");
//			user.setEmail("test@test.com");
		
		
		//==> API Ȯ��
		Assert.assertEquals("user01", purchase.getBuyer().getUserId());
		Assert.assertEquals(10001, purchase.getPurchaseProd().getProdNo());
		Assert.assertEquals("1  ", purchase.getPaymentOption());
		Assert.assertEquals("ȫ�浿",purchase.getReceiverName());
		Assert.assertEquals("01023568956", purchase.getReceiverPhone());
		Assert.assertEquals("gggg", purchase.getDivyRequest());
		Assert.assertEquals("0  ", purchase.getTranCode());
	}
	
	//@Test
	 public void testUpdateUser() throws Exception{
		
		Purchase purchase = purchaseService.getPurchase(10027);
		
		Assert.assertNotNull(purchase);
		
		purchase.setReceiverName("ȫ���");
		purchase.setDivyRequest("�m����m");
		purchase.setPaymentOption("1  ");
		purchase.setReceiverPhone("01023568956");
		purchase.setDivyRequest("��������");
		
		purchaseService.updatePurcahse(purchase);
		
		purchase = purchaseService.getPurchase(10027);
		
		Assert.assertNotNull(purchase);
		
		//==> console Ȯ��
		//System.out.println(user);
			
		//==> API Ȯ��
		
		
		
	 }
	 
	//@Test
	public void testCheckDuplication() throws Exception{

		//==> �ʿ��ϴٸ�...
//			User user = new User();
//			user.setUserId("testUserId");
//			user.setUserName("testUserName");
//			user.setPassword("testPasswd");
//			user.setSsn("1111112222222");
//			user.setPhone("111-2222-3333");
//			user.setAddr("��⵵");
//			user.setEmail("test@test.com");
//			
//			userService.addUser(user);
		
		//==> console Ȯ��
		//System.out.println(userService.checkDuplication("testUserId"));
		//System.out.println(userService.checkDuplication("testUserId"+System.currentTimeMillis()) );
	 	
		//==> API Ȯ��
		//Assert.assertFalse( userService.checkDuplication("testUserId") );
	 	//Assert.assertTrue( userService.checkDuplication("testUserId"+System.currentTimeMillis()) );
		 	
	}
	
	 //==>  �ּ��� Ǯ�� �����ϸ�....
	@Test
	 public void testGetUserListAll() throws Exception{
		 
	 	Search search = new Search();
	 	search.setCurrentPage(1);
	 	search.setPageSize(3);
	 	Map<String,Object> map = purchaseService.getPurchaseList(search, "user01");
	 	
	 	List<Object> list = (List<Object>)map.get("list");
	 	
		//==> console Ȯ��
	 	//System.out.println(list);
	 	
	 	Integer totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 	
	 	System.out.println("=======================================");
	 	
	 	search.setCurrentPage(1);
	 	search.setPageSize(3);
	 	map =  purchaseService.getPurchaseList(search, "user01");
	 	
	 	list = (List<Object>)map.get("list");
	 	
	 	//==> console Ȯ��
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
	 	
		//==> console Ȯ��
	 	//System.out.println(list);
	 	
	 	Integer totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 	
	 	System.out.println("=======================================");
	 	
	 	search.setSearchCondition("0");
	 	search.setSearchKeyword(""+System.currentTimeMillis());
	 	map = productService.getProductList(search);
	 	
	 	list = (List<Object>)map.get("list");
	 	
		//==> console Ȯ��
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
	 	
		//==> console Ȯ��
	 	//System.out.println(list);
	 	
	 	Integer totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 	
	 	System.out.println("=======================================");
	 	
	 	search.setSearchCondition("1");
	 	search.setSearchKeyword(""+System.currentTimeMillis());
	 	map = userService.getUserList(search);
	 	
	 	list = (List<Object>)map.get("list");
	 	Assert.assertEquals(0, list.size());
	 	
		//==> console Ȯ��
	 	//System.out.println(list);
	 	
	 	totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);*/
	 }	 
}