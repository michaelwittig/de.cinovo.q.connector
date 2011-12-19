package de.cinovo.q.connector;

import java.sql.Timestamp;

/**
 * KXTableRow.
 *
 * @author mwittig
 *
 */
public final class KXTableRowImpl implements KXTableRow {

	/** Data. */
	private final KXTable table;

	/** Row. */
	private final int row;

	/**
	 * @param aTable Table
	 * @param aRow Row
	 */
	protected KXTableRowImpl(final KXTable aTable, final int aRow) {
		super();
		this.table = aTable;
		this.row = aRow;
	}

	@Override
	public Object get(final int col) {
		return this.table.getAt(col, this.row);
	}

	@Override
	public Object get(final String col) {
		return this.table.getAt(col, this.row);
	}

	@Override
	public String getString(final String col) {
		return this.table.getStringAt(col, this.row);
	}

	@Override
	public float getFloat(final String col) {
		return this.table.getFloatAt(col, this.row);
	}

	@Override
	public Timestamp getTimestamp(final String col) {
		return this.table.getTimestampAt(col, this.row);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		for (int i = 0; i < this.table.getCols(); i++) {
			sb.append(String.format("%10s ", this.get(i).toString()));
		}
		return sb.toString();
	}

}
