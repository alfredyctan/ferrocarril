package org.afc.carril.fix.mapping.impl;

import org.afc.carril.fix.mapping.Getter;


public class SchemaGetter<S> implements Getter<S> {

	private String index;
	
	public SchemaGetter(String index) {
		this.index = index;
    }
		
	@Override
	public Object get(S source) {
		return index;
	}
}