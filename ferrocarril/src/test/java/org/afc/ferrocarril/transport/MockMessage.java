package org.afc.ferrocarril.transport;

import org.afc.ferrocarril.message.GenericMessage;

public class MockMessage implements GenericMessage {

	private static final long serialVersionUID = 5793189076770049031L;

	public Object getNative() {
		return null;
	}
	
	public void setNative(Object obj) {
	}
	
	public String getProduct() {
		return null;
	}

	public void setProduct(String product) {
	}
}
