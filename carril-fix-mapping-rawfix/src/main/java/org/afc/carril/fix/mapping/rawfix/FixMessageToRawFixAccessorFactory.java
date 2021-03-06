package org.afc.carril.fix.mapping.rawfix;

import org.afc.carril.fix.mapping.AccessorFactory;
import org.afc.carril.fix.mapping.Getter;
import org.afc.carril.fix.mapping.SessionState;
import org.afc.carril.fix.mapping.Setter;
import org.afc.carril.fix.mapping.TagMapper;
import org.afc.carril.fix.mapping.impl.ConstGetter;
import org.afc.carril.fix.mapping.impl.FixMessageGetter;
import org.afc.carril.fix.mapping.impl.SchemaGetter;
import org.afc.carril.fix.mapping.impl.StateGetter;
import org.afc.carril.fix.mapping.impl.StateSetter;
import org.afc.carril.fix.mapping.schema.Reference;
import org.afc.carril.fix.mapping.schema.Type;
import org.afc.carril.fix.mapping.schema.Use;
import org.afc.carril.message.FixMessage;
import org.afc.carril.transport.TransportException;

public class FixMessageToRawFixAccessorFactory implements AccessorFactory<FixMessage, RawFix, Object> {
	
	@Override
	public Getter<FixMessage> createGetter(SessionState state, Reference reference, String index, Type type) {
		switch (reference) {
			case OBJ:
				return new FixMessageGetter<FixMessage>(index);
			case STATE:
				return new StateGetter<FixMessage>(state, new FixMessageGetter<FixMessage>(index));
			case CONST:
				return new ConstGetter<FixMessage>(index, type);
			case SCHEMA:
				return new SchemaGetter<FixMessage>(index);
			default:
				throw new TransportException("Unsupported reference type " + reference);
		}
	}

	@Override
	public Setter<FixMessage, RawFix, Object> createSetter(SessionState state, Reference reference, String index, Type type) {
		switch (reference) {
			case FIX:
			case FIX_HEADER:
			case FIX_TRAILER:
				return new RawFixSetter(index, type);
			case STATE:
				return new StateSetter<FixMessage, RawFix>(state, new FixMessageGetter<FixMessage>(index));
			default:
				throw new TransportException("Unsupported reference type " + reference);
		}
	}
	
	
	@Override
	public TagMapper<FixMessage, RawFix> createTagMapper(String name, 
		Getter<FixMessage> getter, Setter<FixMessage, RawFix, Object> setter, String targetIndex, Use use
	) {
	    return new FixMessageToRawFixTagMapper(name, getter, setter, use);
	}
}
