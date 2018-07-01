package com.tiaa.mproduct.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.log4j.Logger;

import com.tiaa.mproduct.constant.CommonConstant;
import com.tiaa.mproduct.exception.InsufficientRawMaterialException;
import com.tiaa.mproduct.model.Bolt;
import com.tiaa.mproduct.model.Machine;
import com.tiaa.mproduct.model.Product;
import com.tiaa.mproduct.service.ProductAssemblyService;

public class DefaultProductAssemblyService implements ProductAssemblyService {
	
	/** The logger. */
    private final Logger logger = Logger.getLogger(DefaultProductAssemblyService.class);
	
	private static List<Product> productList  = Collections.synchronizedList(new ArrayList<>());
	
	private AtomicInteger productCount = new AtomicInteger(0);
	
	public void assembleProduct(List<Bolt> bolts, Machine machine, int assemblyTime) {
		if (bolts.size() != CommonConstant.PRODUCT_BOLT_SIZE) {
			throw new InsufficientRawMaterialException(" Bolts should be " + CommonConstant.PRODUCT_BOLT_SIZE);
		}
		if (machine == null) {
			throw new InsufficientRawMaterialException(" Machine should be provided");
		}
		
		logger.info("Assembling Product!!" + productCount.getAndIncrement());
		
		try {
			Thread.sleep(assemblyTime * 1000L);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			logger.error("Thread interrupted error!!", e);
		}
		
		productList.add(new Product(bolts, machine));
	}

	public List<Product> getAssembledProducts() {
		return productList;
	}

	public int getAssembledProductsCount() {
		return productCount.get();
	}
}
