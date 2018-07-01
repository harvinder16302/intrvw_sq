package com.tiaa.mproduct.model;

import java.util.List;

public class Product {
	
	private List<Bolt> bolts;
	private Machine machine;
	
	public Product(List<Bolt> bolts, Machine machine) {
		if (bolts == null) {
			throw new NullPointerException("bolts is null");
		}
		if (machine == null) {
			throw new NullPointerException("machine is null");
		}

		this.bolts = bolts;
		this.machine = machine;
	}
	
	public List<Bolt> getBolts() {
		return bolts;
	}
	public void setBolts(List<Bolt> bolts) {
		this.bolts = bolts;
	}
	public Machine getMachine() {
		return machine;
	}
	public void setMachine(Machine machine) {
		this.machine = machine;
	}
	
}
