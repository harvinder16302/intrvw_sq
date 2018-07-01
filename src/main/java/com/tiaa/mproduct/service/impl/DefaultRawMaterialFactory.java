package com.tiaa.mproduct.service.impl;

import org.apache.log4j.Logger;

import com.tiaa.mproduct.ConveyerBelt;
import com.tiaa.mproduct.model.Bolt;
import com.tiaa.mproduct.model.Machine;
import com.tiaa.mproduct.service.RawMaterialFactory;

public class DefaultRawMaterialFactory implements RawMaterialFactory {
	
	/** The logger. */
    private final Logger logger = Logger.getLogger(DefaultRawMaterialFactory.class);
	
	private ConveyerBelt conveyerBelt;
	private int bolts;
	private int machines;

	public DefaultRawMaterialFactory(ConveyerBelt conveyerBelt, int bolts, int machines) {
		this.conveyerBelt = conveyerBelt;
		this.bolts = bolts;
		this.machines = machines;
	}

	public void produceRawMaterial() {
		boolean anyItemLeft = false;
		
		do {
			anyItemLeft = generateBolt();
			anyItemLeft = generateMachine() || anyItemLeft;
			
		} while(anyItemLeft);
		
		logger.info("Raw material Production complete");
	}

	private boolean generateMachine() {
		logger.info("Adding machine: " + machines);
		
		if (machines < 1) {
			return false;
		}
		
		machines--;
		
		conveyerBelt.addRawMaterial(new Machine());

		return machines!=0;
	}

	private boolean generateBolt() {
		logger.info("Adding bolt: " + bolts);
		
		if (bolts < 1) {
			return false;
		}
		
		bolts--;
		
		conveyerBelt.addRawMaterial(new Bolt());
		
		return bolts!=0;
	}

}
