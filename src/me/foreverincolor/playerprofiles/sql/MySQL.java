package me.foreverincolor.playerprofiles.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import me.foreverincolor.playerprofiles.Main;

public class MySQL {

	private String host;
	private String port;
	private String database;
	private String username;
	private String password;

	private Connection connection;
	private Main plugin;

	// Constructor
	public MySQL(Main plugin) {
		this.plugin = plugin;
	}

	// Checks if database is connected
	public boolean isConnected() {
		return (connection == null ? false : true);
	}

	// Connects to database using config
	public void connect() throws ClassNotFoundException, SQLException {
		if (!isConnected()) {
			host = plugin.getConfig().getString("sql.host");
			port = plugin.getConfig().getString("sql.port");
			database = plugin.getConfig().getString("sql.database");
			username = plugin.getConfig().getString("sql.username");
			password = plugin.getConfig().getString("sql.password");

			connection = DriverManager.getConnection(
					"jdbc:mysql://" + host + ":" + port + "/" + database + "?useSSL=false", username, password);
		}
	}

	// Disconnects from database
	public void disconnect() {
		if (isConnected()) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	// Returns connection
	public Connection getConnection() {
		return connection;
	}

}
