// -------------------------------------------------------------------------------
// Copyright (c) 2011-2012 Cinovo AG
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Apache License, Version 2.0
// which accompanies this distribution, and is available at
// http://www.apache.org/licenses/LICENSE-2.0.html
// -------------------------------------------------------------------------------

package de.cinovo.q.query.value.impl;

import java.math.BigDecimal;

import de.cinovo.q.query.type.OrdinalList;
import de.cinovo.q.query.type.impl.TypeFloat;

/**
 * Float value.
 * 
 * @author mwittig
 * 
 */
public final class FloatValue extends AValue<BigDecimal, TypeFloat> {

	/** Null. */
	public static final String NULL = "0n";

	/**
	 * @param value
	 *            Value
	 * @return Float
	 */
	public static FloatValue from(final BigDecimal value) {
		return new FloatValue(value, TypeFloat.get());
	}

	/**
	 * @param values
	 *            Values
	 * @return List of floats
	 */
	public static OrdinalList<BigDecimal, TypeFloat> froms(final BigDecimal[] values) {
		return new OrdinalListImpl<BigDecimal, TypeFloat>(values, TypeFloat.get());
	}

	/**
	 * @param value
	 *            Value
	 * @param type
	 *            Type
	 */
	public FloatValue(final BigDecimal value, final TypeFloat type) {
		super(value, type);
	}

	@Override
	public String toQ() {
		if (this.get() == null) {
			return NULL;
		}
		return this.get() + "f";
	}

}
