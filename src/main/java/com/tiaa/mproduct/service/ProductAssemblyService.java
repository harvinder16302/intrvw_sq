package com.tiaa.mproduct.service;

import java.util.List;

import com.tiaa.mproduct.model.Bolt;
import com.tiaa.mproduct.model.Machine;
import com.tiaa.mproduct.model.Product;

public interface ProductAssemblyService {
	
	/**
	 * Assembles the product in the given assembly time.
	 */
	void assembleProduct(List<Bolt> bolts, Machine machine, int assemblyTime);

	/**
	 *  
	 * @return assembled products
	 */
	List<Product> getAssembledProducts();
	
	int getAssembledProductsCount();
}
