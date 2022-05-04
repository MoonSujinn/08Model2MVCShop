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
import com.model2.mvc.service.product.ProductService;



@RunWith(SpringJUnit4ClassRunner.class)

//==> Meta-Data ¸¦ ´Ù¾çÇÏ°Ô Wiring ÇÏÀÚ...
//@ContextConfiguration(locations = { "classpath:config/context-*.xml" })
@ContextConfiguration	(locations = {	"classpath:config/context-common.xml",
																	"classpath:config/context-aspect.xml",
																	"classpath:config/context-mybatis.xml",
																	"classpath:config/context-transaction.xml" })
//@ContextConfiguration(locations = { "classpath:config/context-common.xml" })
public class ProductServiceTest {
	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;

	//@Test
	public void testAddProduct() throws Exception {
		
		Product product = new Product();
		product.setFileName("¸ù¸ù.jpg");
		product.setManuDate("1111");
		product.setPrice(3000);
		product.setProdDetail("±Í¿©¿ö¿ä");
		product.setProdName("ÀÚ¸ùÀÌ »çÁø");

		
		productService.addProduct(product);
		

		//==> console È®ÀÎ
		System.out.println(product);
		
		//==> API È®ÀÎ
		Assert.assertEquals("¸ù¸ù.jpg", product.getFileName());
		Assert.assertEquals("1111", product.getManuDate());
		Assert.assertEquals(3000, product.getPrice());
		Assert.assertEquals("±Í¿©¿ö¿ä", product.getProdDetail());
		Assert.assertEquals("ÀÚ¸ùÀÌ »çÁø", product.getProdName());

	}

	//@Test
	public void testGetProduct() throws Exception {
		
		Product product = new Product();
		
		product = productService.getProduct(10083);

		//==> console È®ÀÎ
		System.out.println(product);
		
		//==> API È®ÀÎ
		Assert.assertEquals("¸ù¸ù.jpg", product.getFileName());
		Assert.assertEquals("1111", product.getManuDate());
		Assert.assertEquals(3000, product.getPrice());
		Assert.assertEquals("±Í¿©¿ö¿ä", product.getProdDetail());
		Assert.assertEquals("ÀÚ¸ùÀÌ »çÁø", product.getProdName());

		Assert.assertNotNull(productService.getProduct(10083));
	}
	
	//@Test
	 public void testUpdateProduct() throws Exception{
		 
		Product product = productService.getProduct(10083);
		Assert.assertNotNull(product);
		
		Assert.assertEquals("¸ù¸ù.jpg", product.getFileName());
		Assert.assertEquals("1111", product.getManuDate());
		Assert.assertEquals(3000, product.getPrice());
		Assert.assertEquals("±Í¿©¿ö¿ä", product.getProdDetail());
		Assert.assertEquals("ÀÚ¸ùÀÌ »çÁø", product.getProdName());

		product.setFileName("¸ù¸ù¸ù.jpg");
		product.setManuDate("2222");
		product.setPrice(5000);
		product.setProdDetail("¿ÏÀü ±Í¿©¿ö¿ä");
		product.setProdName("ÀÚ¸ù»çÁø");
		
		productService.updateProduct(product);
		
		product = productService.getProduct(10083);
		Assert.assertNotNull(product);
		
		//==> console È®ÀÎ
		System.out.println(product);
			
		//==> API È®ÀÎ
		Assert.assertEquals("¸ù¸ù¸ù.jpg", product.getFileName());
		Assert.assertEquals("2222", product.getManuDate());
		Assert.assertEquals(5000, product.getPrice());
		Assert.assertEquals("¿ÏÀü ±Í¿©¿ö¿ä", product.getProdDetail());
		Assert.assertEquals("ÀÚ¸ù»çÁø", product.getProdName());
	 }
	 
	 //@Test
	 public void testGetProductListAll() throws Exception{
		 
	 	Search search = new Search();
	 	search.setCurrentPage(1);
	 	search.setPageSize(3);
	 	Map<String,Object> map = productService.getProductList(search);
	 	
	 	List<Object> list = (List<Object>)map.get("list");
	 	Assert.assertEquals(3, list.size());
	 	
		//==> console È®ÀÎ
	 	System.out.println(list);
	 	
	 	Integer totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 	
	 	System.out.println("=======================================");
	 	
	 }
	 
	 //@Test
	 public void testGetProductListByProdNo() throws Exception{
		 
		 	Search search = new Search();
		 	search.setCurrentPage(1);
		 	search.setPageSize(3);
		 	search.setSearchCondition("0");
		 	search.setSearchKeyword("10083");
		 	Map<String,Object> map = productService.getProductList(search);
		 	
		 	List<Object> list = (List<Object>)map.get("list");
		 	Assert.assertEquals(1, list.size());
		 	
			//==> console È®ÀÎ
		 	System.out.println(list);
		 	
		 	Integer totalCount = (Integer)map.get("totalCount");
		 	System.out.println(totalCount);
		 	
		 	System.out.println("=======================================");

		 }
	 
	 //@Test
	 public void testGetProductListByProdName() throws Exception{
		 
	 	Search search = new Search();
	 	search.setCurrentPage(1);
	 	search.setPageSize(3);
	 	search.setSearchCondition("1");
	 	search.setSearchKeyword("ÀÚ¸ù»çÁø");
	 	Map<String,Object> map = productService.getProductList(search);
	 	
	 	List<Object> list = (List<Object>)map.get("list");
	 	Assert.assertEquals(2, list.size());
	 	
		//==> console È®ÀÎ
	 	System.out.println(list);
	 	
	 	Integer totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 	
	 	System.out.println("=======================================");
	 	
	 }	 
	 
	 @Test
	 public void testGetProductListByPrice() throws Exception{
		 
	 	Search search = new Search();
	 	search.setCurrentPage(1);
	 	search.setPageSize(3);
	 	search.setSearchCondition("2");
	 	search.setSearchKeyword("5000");
	 	Map<String,Object> map = productService.getProductList(search);
	 	
	 	List<Object> list = (List<Object>)map.get("list");
	 	Assert.assertEquals(2, list.size());
	 	
		//==> console È®ÀÎ
	 	System.out.println(list);
	 	
	 	Integer totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 	
	 	System.out.println("=======================================");
	 	

	 }
	

	
	 
}