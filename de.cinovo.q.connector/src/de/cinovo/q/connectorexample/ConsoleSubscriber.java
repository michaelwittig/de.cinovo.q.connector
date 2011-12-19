package de.cinovo.q.connectorexample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import de.cinovo.q.connector.KXConnector;
import de.cinovo.q.connector.KXConnectorFactory;
import de.cinovo.q.connector.KXConnectorListener;
import de.cinovo.q.connector.KXError;
import de.cinovo.q.connector.KXException;
import de.cinovo.q.connector.KXTable;
import de.cinovo.q.connector.KXTableRow;

/**
 * Console subscriber.
 *
 * @author mwittig
 *
 */
public final class ConsoleSubscriber implements KXConnectorListener {

	/** KX connector. */
	private final KXConnector c = KXConnectorFactory.create(this, "192.168.64.61", 5010, true);

	/**
	 * @param args Arguments not needed
	 */
	public static void main(final String[] args) {
		try {
			final ConsoleSubscriber console = new ConsoleSubscriber();
			console.start();
			System.out.println("ConsoleSubscriber: Enter a command...");
			final BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			String line = "";
			while ((line = in.readLine()) != null) {
				System.out.println("Command: " + line);
				final String[] l = line.split(" ");
				if (l[0].equals("quit")) {
					break;
				} else if (l[0].equals("start")) {
					console.start();
				} else if (l[0].equals("stop")) {
					console.stop();
				} else if (l[0].equals("sub") || l[0].equals("subscribe")) {
					console.subscribe(l[1], l[2]);
				} else {
					System.err.println("Unknown command: " + line);
				}
			}
			in.close();
			console.stop();
			System.out.println("Good bye");
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

	/** */
	public ConsoleSubscriber() {
		super();
	}

	/**
	 * Start.
	 */
	public void start() {
		try {
			this.c.connect();
		} catch (final KXException e) {
			System.err.println("KXException: " + e);
		} catch (final KXError e) {
			System.err.println("KXError: " + e);
		}
	}

	/**
	 * Stop.
	 */
	public void stop() {
		try {
			this.c.disconnect();
		} catch (final KXError e) {
			System.err.println("KXError: " + e);
		}
	}

	/**
	 * Subscribe.
	 *
	 * @param table Table
	 * @param symbol Symbol
	 */
	public void subscribe(final String table, final String symbol) {
		try {
			this.c.subscribe("", new String[] {table}, new String[] {symbol});
		} catch (final KXException e) {
			System.err.println("KXException: " + e);
			e.printStackTrace();
		}
	}

	@Override
	public void error(final KXError e) {
		System.err.println("KXError: " + e);
	}

	@Override
	public void exception(final KXException e) {
		System.err.println("KXException: " + e);
		e.printStackTrace();
	}

	@Override
	public void dataReceived(final String handle, final KXTable data) {
		System.out.println(data);
	}

}
