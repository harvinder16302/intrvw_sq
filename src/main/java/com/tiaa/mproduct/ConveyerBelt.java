package com.tiaa.mproduct;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import org.apache.log4j.Logger;

import com.tiaa.mproduct.model.RawMaterial;
import com.tiaa.mproduct.service.impl.DefaultProductAssemblyService;

public class ConveyerBelt {
	
	/** The logger. */
    private final Logger logger = Logger.getLogger(DefaultProductAssemblyService.class);

	private BlockingQueue<RawMaterial> conveyerBeltQueue;
	
	public ConveyerBelt(int rawMaterialCount) {
		
		conveyerBeltQueue = new ArrayBlockingQueue<>(rawMaterialCount);
	}

	/**
	 * Picks one raw material at a time from conveyerBelt
	 */
	public RawMaterial getRawMaterial(){
		try {
			logger.info("Getting raw material");
			
			return conveyerBeltQueue.take();
			
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			logger.error("Thread interrupted error!!", e);
		}
		
		return null;
	}

	/**
	 * Adds Raw material to the conveyerBelt
	 * @param rawMaterial
	 */
	public boolean addRawMaterial(RawMaterial rawMaterial){
		try {
			logger.info("Adding raw material");
			
			conveyerBeltQueue.put(rawMaterial);
			return true;
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			logger.error("Thread interrupted error!!", e);
		}
		
		return false;
	}
	 
	public boolean isRawMaterialConsumed () {
		return conveyerBeltQueue.isEmpty();
	}

}
