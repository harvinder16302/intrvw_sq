package com.tiaa.mproduct;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.tiaa.mproduct.model.Bolt;
import com.tiaa.mproduct.model.Machine;
import com.tiaa.mproduct.model.Product;
import com.tiaa.mproduct.service.ProductAssemblyService;

import static org.junit.Assert.*;


public class ProductAssemblyUnitTest {

	private ProductAssemblyService productServiceMock;

	@Before
	public void setupProductServiceMock() {
		productServiceMock = Mockito.mock(ProductAssemblyService.class);

		Mockito.when(productServiceMock.getAssembledProducts())
		.thenReturn(Arrays.asList(new Product(Arrays.asList(new Bolt(), new Bolt()), new Machine())));
	}
	
	@Test
	public void testProductAssembly() {
		List<Product> products = productServiceMock.getAssembledProducts();
		
		for (Product product : products) {
			assertTrue("Assembled product should have two bolts", product.getBolts().size() == 2);
			assertTrue("Assembled product should have a machine", product.getMachine() != null);
		}
	}
}
