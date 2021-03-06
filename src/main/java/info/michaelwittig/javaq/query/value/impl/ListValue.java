package info.michaelwittig.javaq.query.value.impl;

import info.michaelwittig.javaq.query.type.Type;
import info.michaelwittig.javaq.query.type.impl.TypeList;
import info.michaelwittig.javaq.query.value.Value;

import java.util.ArrayList;

/**
 * List value.
 * 
 * @author mwittig
 * 
 * @param <J> Java type
 * @param <T> Type
 */
public class ListValue<J, T extends TypeList<J, Type<J>>> extends AValue<J[], T> {
	
	/** Values. */
	private final ArrayList<Value<J, ? extends Type<J>>> values;
	
	
	/**
	 * @param aValue Value
	 * @param aType Type
	 */
	public ListValue(final J[] aValue, final T aType) {
		super(aValue, aType);
		if (aValue != null) {
			this.values = new ArrayList<Value<J, ? extends Type<J>>>();
			for (final J value : aValue) {
				this.values.add(aType.getItemType().geValueFactory().create(value));
			}
		} else {
			this.values = null;
		}
		
	}
	
	@Override
	public final String toQ() {
		final StringBuilder sb = new StringBuilder();
		if (this.values != null) {
			sb.append("(");
			if (this.values.size() > 0) {
				for (final Value<J, ? extends Type<J>> value : this.values) {
					sb.append(value.toQ());
					sb.append(",");
				}
				sb.deleteCharAt(sb.length() - 1);
			}
			sb.append(")");
		} else {
			sb.append("()");
		}
		return sb.toString();
	}
	
}
