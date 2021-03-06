package org.afc.carril.fix.mapping.quickfix;

import org.afc.carril.fix.mapping.Getter;
import org.afc.carril.fix.mapping.schema.Type;

import quickfix.FieldMap;
import quickfix.Message;

public class QuickFixTrailerGetter implements Getter<Message> {

	private Getter<FieldMap> getter;
	
	public QuickFixTrailerGetter(String index, Type type) {
		getter = new QuickFixFieldGetter(index, type);
    }

	@Override
	public Object get(Message source) {
	    return getter.get(source.getTrailer());
	}
}
