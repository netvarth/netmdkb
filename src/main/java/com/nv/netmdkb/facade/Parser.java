package com.nv.netmdkb.facade;

import java.util.List;

public interface Parser<T> {

	public  List<T> parse(String xmlpath);
	
	
}
