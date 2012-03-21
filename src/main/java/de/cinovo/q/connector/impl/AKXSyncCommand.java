package de.cinovo.q.connector.impl;

import java.io.IOException;

import kx.c;
import kx.c.KException;
import de.cinovo.q.connector.KXException;
import de.cinovo.q.query.FlipFlipResult;
import de.cinovo.q.query.FlipResult;
import de.cinovo.q.query.Result;

/**
 * Abstract kx synchronous command.
 *
 * @author mwittig
 *
 */
public abstract class AKXSyncCommand implements KXSyncCommand {

	/**
	 * @param c C
	 * @param q Q
	 * @return Result
	 * @throws KXException If result is not a table
	 * @throws KException If something went wrong
	 * @throws IOException If something went wrong
	 */
	protected final Result execute(final c c, final String q) throws KXException, KException, IOException {
		final Object res = c.k(q);
		if (res instanceof c.Flip) {
			return new FlipResult((c.Flip) res);
		} else if (res instanceof c.Dict) {
			final c.Dict dict = (c.Dict) res;
			if (dict.x instanceof c.Flip && dict.y instanceof c.Flip) {
				final c.Flip key = (c.Flip) dict.x;
				final c.Flip data = (c.Flip) dict.y;
				return new FlipFlipResult(key, data);
			}
		}
		throw new KXException("Result is not a flip");
	}
}