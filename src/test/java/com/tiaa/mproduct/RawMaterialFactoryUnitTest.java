package com.tiaa.mproduct;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.tiaa.mproduct.service.RawMaterialFactory;
import com.tiaa.mproduct.service.impl.DefaultRawMaterialFactory;

public class RawMaterialFactoryUnitTest {
	
	private RawMaterialFactory productServiceMock;

	@Before
	public void setupProductServiceMock() {
		ConveyerBelt conveyerBelt = Mockito.mock(ConveyerBelt.class);
		productServiceMock = new DefaultRawMaterialFactory(conveyerBelt, 2, 1);
				
	}
	
	@Test
	public void testProductAssembly() {
		productServiceMock.produceRawMaterial();;
	}
}
