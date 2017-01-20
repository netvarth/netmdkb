package com.nv.netmdkb.analytic.bl.dataCluster;

public enum ValueType {
	
	INTEGER(Integer.class, new IntegerParser()),
	FLOAT(Float.class,new FloatParser()),
	STRING(String.class,new StringParser());
	
	
	private Class<?> clazz;
	private Parser parser;
	
	private ValueType(Class<?> clazz,Parser parser){
		this.clazz=clazz;
		this.parser=parser;
	}

	public Class<?> getClazz() {
		return clazz;
	}

	public Parser getParser() {
		return parser;
	}
	
	
	 
	
}
