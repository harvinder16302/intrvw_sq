package com.tiaa.mproduct;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;

import com.tiaa.mproduct.constant.CommonConstant;
import com.tiaa.mproduct.model.Bolt;
import com.tiaa.mproduct.model.Machine;
import com.tiaa.mproduct.model.RawMaterial;
import com.tiaa.mproduct.service.ProductAssemblyService;

/**
 * EmployeesThread gets raw material and assemble a product
 * @author HarvinderSingh
 *
 */
public class EmployeeThread implements Runnable {
	
	/** The logger. */
    private final Logger logger = Logger.getLogger(EmployeeThread.class);
	
	private String employeeName;
	private ConveyerBelt conveyerBelt;
	private ProductAssemblyService productAssemblyService;
	private int assemblyTime;
	
	
	public EmployeeThread(String employeeName, int assemblyTime, ConveyerBelt conveyerBelt,
			ProductAssemblyService productAssemblyService) {
		this.employeeName = employeeName;
		this.conveyerBelt = conveyerBelt;
		this.productAssemblyService = productAssemblyService;
		this.assemblyTime = assemblyTime;
	}

	public void run() {
		Thread.currentThread().setName(employeeName);
		
		generateProduct();
		
	}

	private void generateProduct() {
		
		logger.info("Product generation started by employee: " + employeeName);
		
		RawMaterial rawMaterial;
		final List<Bolt> boltsList = Collections.synchronizedList(new ArrayList<>());
		Machine machine = null;
		
		/**
		 * Check whether raw material is consumed
		 */
		while(true) {
			
			// Function 
			rawMaterial = conveyerBelt.getRawMaterial();
			
			if (rawMaterial == null) {
				logger.info("Raw Material finished..");
				break;
			}
			
			if (rawMaterial.getType().equals(CommonConstant.BOLT_TYPE)) {
				
				if (boltsList.size() < CommonConstant.PRODUCT_BOLT_SIZE) {
					boltsList.add((Bolt) rawMaterial);
				}
			} 
			
			else if (machine == null) {
				machine = new Machine();
			}
			
			/* If minimum condition meets send for assembly*/
			if (boltsList.size() == CommonConstant.PRODUCT_BOLT_SIZE && machine != null) {
				logger.info("Bolts:" + boltsList.size() + " machines : " + (machine!= null));
				
				productAssemblyService.assembleProduct(Collections.unmodifiableList(boltsList), machine, assemblyTime);
				
			}
			
		}
		
		logger.info("Product generation completed by.. " + employeeName);
	}
	
}
