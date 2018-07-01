package com.tiaa.mproduct;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import com.tiaa.mproduct.constant.CommonConstant;
import com.tiaa.mproduct.service.ProductAssemblyService;
import com.tiaa.mproduct.service.impl.DefaultProductAssemblyService;
import com.tiaa.mproduct.service.impl.DefaultRawMaterialFactory;

/**
 * MProduct Application
 * @author HarvinderSingh
 *
 */
public class MProductApplication {

	/** The logger. */
	private static final Logger logger = Logger.getLogger(MProductApplication.class);

	public static void main(String[] args) {
		try (Scanner sc = new Scanner(System.in)) {

			logger.info("Enter bolts count : ");
			int boltsCount = sc.nextInt();

			logger.info("Enter machines count : ");
			int machinesCount = sc.nextInt();

			logger.info("Enter per product assembly time (in seconds): ");
			int assemblyTime = sc.nextInt();

			int rawMaterialCount = boltsCount + machinesCount;
			
			ConveyerBelt conveyerBelt = new ConveyerBelt(rawMaterialCount);
			
			ProductAssemblyService productAssemblyService = new DefaultProductAssemblyService(); 

			DefaultRawMaterialFactory rawMaterialFactory = new DefaultRawMaterialFactory(conveyerBelt, boltsCount, machinesCount);
			
			/*
			 * factory generating raw material.
			 */
			rawMaterialFactory.produceRawMaterial();

			ExecutorService executor = Executors.newFixedThreadPool(
					CommonConstant.EMPLOYEE_COUNT);
			
			long startTime = System.currentTimeMillis();

			String[] employees = getEmployees();

			for (int i=0; i<CommonConstant.EMPLOYEE_COUNT; i++) {
				executor.execute(new EmployeeThread(employees[i], assemblyTime, conveyerBelt, productAssemblyService));
			}

			
			executor.shutdown();
			executor.awaitTermination(assemblyTime, TimeUnit.SECONDS);

			long endTime = System.currentTimeMillis();
			
			logger.info("Total Products: " + productAssemblyService.getAssembledProductsCount());
			logger.info("Total time taken: " + (endTime-startTime)/1000);
			
		} catch (InterruptedException ie) {
			Thread.currentThread().interrupt(); 
		}
	}

	/**
	 * 
	 * @return employee array
	 */
	private static String[] getEmployees() {
		return new String[] {"Emp1", "Emp2", "Emp3"};
	}
}
